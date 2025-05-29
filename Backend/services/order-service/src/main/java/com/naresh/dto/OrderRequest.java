package com.naresh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.naresh.model.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OrderRequest(
        String reference,
        @Positive(message="order amount should be positive")
        BigDecimal amount,
        @NotNull(message="Payment method should be present")
        PaymentMethod paymentMethod,
        @NotNull(message="customer should be present")
        @NotEmpty(message ="customer should be present" )
        @NotBlank(message ="customer should be present" )
        String customerId,
        @NotEmpty(message="you should at least purchase one product")
        List<PurchaseRequest> products
) {
}
