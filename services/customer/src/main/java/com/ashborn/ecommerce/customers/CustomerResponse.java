package com.ashborn.ecommerce.customers;

    
public record CustomerResponse(
        Long id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
   
}
