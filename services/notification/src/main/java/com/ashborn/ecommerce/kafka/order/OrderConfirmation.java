package com.ashborn.ecommerce.kafka.order;

import java.math.BigDecimal;
import java.util.List;

import com.ashborn.ecommerce.kafka.payment.PaymentMethod;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderConfirmation {

    private String orderReference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;

    @Embedded
    private Customer customer;

    @ElementCollection
    private List<Product> products;
}
