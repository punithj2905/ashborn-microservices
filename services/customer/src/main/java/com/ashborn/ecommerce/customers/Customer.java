package com.ashborn.ecommerce.customers;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Customer {
     @Id
    private String id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Embedded
    private Address address;
   
}
