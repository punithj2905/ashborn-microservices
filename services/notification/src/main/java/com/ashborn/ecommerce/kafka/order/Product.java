package com.ashborn.ecommerce.kafka.order;

import java.math.BigDecimal;

import jakarta.persistence.Embeddable;

@Embeddable
public record Product(
    Integer productId,
    String name,
    String description,
    BigDecimal price,
    double quantity
) {

}
