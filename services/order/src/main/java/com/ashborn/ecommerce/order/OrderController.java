package com.ashborn.ecommerce.order;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;
    @PostMapping("path")
    public ResponseEntity<Integer> createOdrer(
        @RequestBody @Valid OrderRequest request
    ){
       return ResponseEntity.ok(service.createOrder(request));
    }
    
    
}
