package com.ashborn.ecommerce.notification;

import java.time.LocalDateTime;

import com.ashborn.ecommerce.kafka.order.OrderConfirmation;
import com.ashborn.ecommerce.kafka.payment.PaymentConfirmation;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    private LocalDateTime notificationDate;
    @Embedded
    private OrderConfirmation orderConfirmation;
    @Embedded
    private PaymentConfirmation paymentConfirmation;

}
