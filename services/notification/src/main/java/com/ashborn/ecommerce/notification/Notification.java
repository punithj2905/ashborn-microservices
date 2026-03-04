package com.ashborn.ecommerce.notification;

import java.time.LocalDateTime;

import com.ashborn.ecommerce.kafka.order.OrderConfirmation;
import com.ashborn.ecommerce.kafka.payment.PaymentConfirmation;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
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
    private String id;
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    private LocalDateTime notificationDate;
    @Embedded
    @AttributeOverrides({
    @AttributeOverride(name = "orderReference", column = @Column(name = "order_reference")),
    @AttributeOverride(name = "totalAmount", column = @Column(name = "order_total_amount")),
    @AttributeOverride(name = "paymentMethod", column = @Column(name = "order_payment_method")),
    @AttributeOverride(name = "customer.id", column = @Column(name = "order_customer_id")),
    @AttributeOverride(name = "customer.firstname", column = @Column(name = "order_customer_firstname")),
    @AttributeOverride(name = "customer.lastname", column = @Column(name = "order_customer_lastname")),
    @AttributeOverride(name = "customer.email", column = @Column(name = "order_customer_email"))
    })
    private OrderConfirmation orderConfirmation;
    @Embedded
    @AttributeOverrides({
     @AttributeOverride(name = "orderReference", column = @Column(name = "payment_order_reference")),
    @AttributeOverride(name = "amount", column = @Column(name = "payment_amount")),
    @AttributeOverride(name = "paymentMethod", column = @Column(name = "payment_payment_method")),
    @AttributeOverride(name = "customerFirstName", column = @Column(name = "payment_customer_firstname")),
    @AttributeOverride(name = "customerLastName", column = @Column(name = "payment_customer_lastname")),
    @AttributeOverride(name = "customerEmail", column = @Column(name = "payment_customer_email"))
   })
    private PaymentConfirmation paymentConfirmation;

}
