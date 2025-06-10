package com.naresh.kafka.payment;



import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String CustomerName,
        String email
) {
}
