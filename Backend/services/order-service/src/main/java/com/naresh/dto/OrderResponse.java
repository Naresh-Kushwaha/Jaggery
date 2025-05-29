package com.naresh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.naresh.model.PaymentMethod;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OrderResponse(
        Long id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Long customerId
) {
}
