package com.naresh.kafka.payment;

import java.math.BigDecimal;

public record PaymentRequest(
     BigDecimal amount,
     PaymentMethod paymentMethod,
     Long orderId,
     String orderReference,
     String customerName,
     String email

) {
}
