package com.ashborn.ecommerce.order;

import org.springframework.stereotype.Service;

import com.ashborn.ecommerce.customer.CustomerClient;
import com.ashborn.ecommerce.exception.BusinessException;
import com.ashborn.ecommerce.orderline.OrderLineRequest;
import com.ashborn.ecommerce.orderline.OrderLineService;
import com.ashborn.ecommerce.product.ProductClient;
import com.ashborn.ecommerce.product.PurchaseRequest;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class OrderService {
     
   private final OrderRespository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    public Integer createOrder(OrderRequest request) {
    //check the customer-->openfeign
    var customer = this.customerClient.findCustomerById(request.customerId())
                       .orElseThrow(()-> new BusinessException("Cannot create order:: No customer exists with the provided Id"));
     


    //purchase the products --> product-miroservice(RestTemplate)
     this.productClient.purchaseProducts(request.products());

    //persist order
    var order=this.repository.save(mapper.toOrder(request));

    //persist the order lines
    for(PurchaseRequest purchaseRequest: request.products()){
        orderLineService.saveOrderLine(
            new OrderLineRequest(
                 null,
                 order.getId(),
                 purchaseRequest.productId(),
                 purchaseRequest.quantity()
            )
        );
    }

    //start payment process


    //send the order confirmation ---> notification-ms (kafka)
    return null;
    }

}
