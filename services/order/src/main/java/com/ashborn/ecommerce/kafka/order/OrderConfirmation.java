package com.ashborn.ecommerce.kafka.order;

import java.math.BigDecimal;
import java.util.List;

import com.ashborn.ecommerce.customer.CustomerResponse;
import com.ashborn.ecommerce.order.PaymentMethod;
import com.ashborn.ecommerce.product.PurchaseResponse;

public record OrderConfirmation(
    String orderReference,
    BigDecimal totalAmount,
    PaymentMethod paymentMethod,
    CustomerResponse customer,
    List<PurchaseResponse> products
) {

}
