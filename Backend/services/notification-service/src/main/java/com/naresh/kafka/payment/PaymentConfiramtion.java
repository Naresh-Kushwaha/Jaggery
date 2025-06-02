package com.naresh.kafka.payment;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentConfiramtion{
       String orderReference;
       BigDecimal amount;
       PaymentMethod paymentMethod;
       String customerName;
       String email;

}
