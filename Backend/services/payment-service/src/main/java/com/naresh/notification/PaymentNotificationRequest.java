package com.naresh.notification;

import com.naresh.dto.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentNotificationRequest{
    private String orderReference;
    private   BigDecimal amount;
    private String paymentMethod;
    private String CustomerName;
    private String email;
    private String paymentId;

}