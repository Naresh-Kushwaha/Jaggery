package com.naresh.dto;

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
