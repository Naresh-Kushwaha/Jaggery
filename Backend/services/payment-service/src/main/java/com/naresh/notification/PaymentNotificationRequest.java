package com.naresh.notification;

import com.naresh.dto.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String CustomerName,
        String email
) {
}
