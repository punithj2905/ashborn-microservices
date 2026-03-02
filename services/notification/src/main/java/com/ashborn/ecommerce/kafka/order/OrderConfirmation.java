package com.ashborn.ecommerce.kafka.order;

import java.math.BigDecimal;
import java.util.List;

import com.ashborn.ecommerce.kafka.payment.PaymentMethod;

import jakarta.persistence.Embeddable;

@Embeddable

public record OrderConfirmation(
    
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    Customer customer,
    List<Product> products
) {
    
}
