package com.ashborn.ecommerce.kafka;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.ashborn.ecommerce.email.EmailService;
import com.ashborn.ecommerce.kafka.order.OrderConfirmation;
import com.ashborn.ecommerce.kafka.payment.PaymentConfirmation;
import com.ashborn.ecommerce.notification.Notification;
import com.ashborn.ecommerce.notification.NotificationRepository;
import com.ashborn.ecommerce.notification.NotificationType;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    
    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation)
            throws MessagingException {

        log.info("Consuming payment-topic :: {}", paymentConfirmation);

        Notification notification = Notification.builder()
                .id(UUID.randomUUID().toString())
                .type(NotificationType.PAYMENT_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .paymentConfirmation(paymentConfirmation)
                .build();

        repository.save(notification);

        String customerName =
                paymentConfirmation.getCustomerFirstName() + " "
                        + paymentConfirmation.getCustomerLastName();

        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.getCustomerEmail(),
                customerName,
                paymentConfirmation.getAmount(),
                paymentConfirmation.getOrderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation)
            throws MessagingException {

        log.info("Consuming order-topic :: {}", orderConfirmation);

        Notification notification = Notification.builder()
                .id(UUID.randomUUID().toString())
                .type(NotificationType.ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .orderConfirmation(orderConfirmation)
                .build();

        repository.save(notification);

        String customerName =
                orderConfirmation.getCustomer().getFirstName() + " "
                        + orderConfirmation.getCustomer().getLastName();

        emailService.sendOrderConfirmationEmail(
                orderConfirmation.getCustomer().getEmail(),
                customerName,
                orderConfirmation.getTotalAmount(),
                orderConfirmation.getOrderReference(),
                orderConfirmation.getProducts()
        );
    }

    
}
