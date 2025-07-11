package com.naresh.kafka.order;

import com.naresh.dto.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmationDTO(
        Long orderReference,
        String email,
        BigDecimal totalAmount,

        List<PurchaseResponse>products
) {
}
