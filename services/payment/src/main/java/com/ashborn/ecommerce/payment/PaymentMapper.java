package com.ashborn.ecommerce.payment;

public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
             .id(request.id())
             .orderId(request.orderId())
             .paymentMethod(request.paymentMethod())
             .amount(request.amount())
        .build();
        
    }

}
