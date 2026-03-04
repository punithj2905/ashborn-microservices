package com.ashborn.ecommerce.order;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
       public Order toOrder(OrderRequest request, String customerId, BigDecimal totalAmount){
        return  Order.builder()
                   .customerId(request.customerId())
                   .reference(UUID.randomUUID().toString())
                   .totalAmount(totalAmount)
                   .paymentMethod(request.paymentMethod())        
                   .build();
       }
       public OrderResponse fromOrder(Order order){
              return new OrderResponse(
                     order.getId(),
                     order.getReference(),
                     order.getTotalAmount(),
                     order.getPaymentMethod(),
                     order.getCustomerId()
              );
       }
}
