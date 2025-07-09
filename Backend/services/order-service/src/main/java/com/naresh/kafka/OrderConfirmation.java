package com.naresh.kafka;

import com.naresh.dto.PurchaseResponse;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        Long orderReference,
        BigDecimal totalAmount,
        List<PurchaseResponse> products

) {
}
