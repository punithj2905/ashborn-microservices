package com.ashborn.ecommerce.customers;

    
public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
   
}
