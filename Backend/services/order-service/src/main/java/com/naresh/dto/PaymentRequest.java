package com.naresh.dto;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        Long orderReference

) {
}
