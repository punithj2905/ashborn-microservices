package com.ashborn.ecommerce.payment;

import java.math.BigDecimal;

import com.ashborn.ecommerce.customer.CustomerResponse;
import com.ashborn.ecommerce.order.PaymentMethod;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    String orderReference,
    CustomerResponse customer
) {

}
