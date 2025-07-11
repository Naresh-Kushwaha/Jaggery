package com.naresh.kafka.payment;

import java.math.BigDecimal;

public record PaymentConfirmationDTO(
        String orderReference,
        String email,
        String paymentMethod,
        BigDecimal amount
) {
}
