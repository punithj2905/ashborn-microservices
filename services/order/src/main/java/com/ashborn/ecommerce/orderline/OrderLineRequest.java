package com.ashborn.ecommerce.orderline;

public record OrderLineRequest(
    Integer orderId,
    Integer productId,
    double quantity
) {

}
