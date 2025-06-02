package com.naresh.kafka.payment.order;

import com.naresh.kafka.payment.PaymentMethod;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class OrderConfirmation{
        String orderReference;
        BigDecimal totalAmount;
        @Enumerated(EnumType.STRING)
        PaymentMethod paymentMethod;
        Customer customer;
        List<Product> products;
}
