package com.ashborn.ecommerce.kafka.order;

import org.antlr.v4.runtime.misc.NotNull;

public record Customer(
    String id, 
    String firstname, 
    String lastname,
    String email
) {

}
