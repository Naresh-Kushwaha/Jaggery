package com.naresh.kafka;

import com.naresh.dto.CustomerResponse;
import com.naresh.dto.PurchaseResponse;
import com.naresh.model.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
