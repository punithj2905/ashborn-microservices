package com.ashborn.ecommerce.order;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ashborn.ecommerce.customer.CustomerClient;
import com.ashborn.ecommerce.exception.BusinessException;
import com.ashborn.ecommerce.kafka.order.OrderConfirmation;
import com.ashborn.ecommerce.kafka.order.OrderProducer;
import com.ashborn.ecommerce.orderline.OrderLineRequest;
import com.ashborn.ecommerce.orderline.OrderLineService;
import com.ashborn.ecommerce.payment.PaymentClient;
import com.ashborn.ecommerce.payment.PaymentRequest;
import com.ashborn.ecommerce.product.ProductClient;
import com.ashborn.ecommerce.product.PurchaseRequest;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    @Transactional
    public Integer createOrder(OrderRequest request) {
        // check the customer-->openfeign
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(
                        () -> new BusinessException("Cannot create order:: No customer exists with the provided Id"));

        // purchase the products --> product-microservice(RestTemplate)
        var purchasedProducts = this.productClient.purchaseProducts(request.products());
        if (purchasedProducts == null || purchasedProducts.isEmpty()) {
            throw new BusinessException("Product purchase failed");
        }
        // calculating total amount
        var totalAmount = purchasedProducts.stream()
                .map(p -> p.price().multiply(
                        BigDecimal.valueOf(p.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        // persist order
        var order = repository.save(
                mapper.toOrder(
                        request,
                        customer.id(),
                        totalAmount));

        order = repository.save(order);

        // persist the order lines
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()));
        }

        // start payment process
        var paymentRequest = new PaymentRequest(
                totalAmount,
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer

        );
        paymentClient.requestOrderPayment(paymentRequest);

        // send the order confirmation ---> notification-ms (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        order.getReference(),
                        totalAmount,
                        request.paymentMethod(),
                        customer,
                        purchasedProducts));
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());

    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No order found with provided Id: %d", orderId)));
    }

}
