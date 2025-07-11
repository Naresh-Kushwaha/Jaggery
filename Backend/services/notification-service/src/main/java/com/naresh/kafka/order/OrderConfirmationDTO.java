package com.naresh.kafka.order;

import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmationDTO(
        String orderReference,
        String email,
      BigDecimal totalAmount,
         List<PurchaseResponse>products
) {
}
