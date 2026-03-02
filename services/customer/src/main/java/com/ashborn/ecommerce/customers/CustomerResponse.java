package com.ashborn.ecommerce.customers;

    
public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
   
}
