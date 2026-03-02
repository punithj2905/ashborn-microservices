package com.ashborn.ecommerce.customers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


public record CustomerRequest(

        String id,
        @NotBlank(message="Customer firstname is required")
        String firstName,
        @NotBlank(message="Customer lastname is required")
        String lastName,
        @NotBlank(message="Customer email is required")
        @Email(message= "Customer email is not a valid email")
        String email,
        Address address) {

}
