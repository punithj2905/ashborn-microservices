package com.ashborn.ecommerce.payment;

import org.springframework.stereotype.Service;

import com.ashborn.ecommerce.kafka.payment.PaymentConfirmation;
import com.ashborn.ecommerce.notification.NotificationProducer;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;
    @Transactional
    public Integer createPayment(PaymentRequest request){
        var payment=repository.save(mapper.toPayment(request));
        log.info("Payment request payload: {}", request);
        notificationProducer.sendNotification(
            new PaymentConfirmation(
                request.orderReference(),
                payment.getAmount(),
                request.paymentMethod(),
                request.customer().firstName(),
                request.customer().lastName(),
                request.customer().email()
            )
        );
        return payment.getId();
    }

}
