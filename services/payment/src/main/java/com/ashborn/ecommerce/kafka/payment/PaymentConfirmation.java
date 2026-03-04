package com.ashborn.ecommerce.kafka.payment;

import java.math.BigDecimal;

import com.ashborn.ecommerce.payment.PaymentMethod;

public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
        
}
