package com.naresh.kafka.order;

import com.naresh.kafka.payment.PaymentMethod;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Entity
@Data
public class OrderConfirmation{
        @Id
        private  String orderReference;
        private   BigDecimal totalAmount;
        @Enumerated(EnumType.STRING)
        private  PaymentMethod paymentMethod;
        @Embedded
        @ElementCollection
        private List<Long> products;
}
