package com.ashborn.ecommerce.orderline;

import org.springframework.stereotype.Service;

import com.ashborn.ecommerce.order.Order;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
               .id(request.orderId())
               .quantity(request.quantity())
               .order(
                  Order.builder()
                       .id(request.id())
                       .build()
                )
               .productId(request.productId())
        .build();
       
    }
    public OrderLineResponse toOrderLineResponse(OrderLine orderLine){
        return new OrderLineResponse( orderLine.getId(), orderLine.getQuantity());
    }

}
