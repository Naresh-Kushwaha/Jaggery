package com.naresh.notification;
import com.naresh.kafka.payment.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentNotificationRequest{
       private String orderReference;
       private   BigDecimal amount;
       private PaymentMethod paymentMethod;
       private String CustomerName;
       private String email;

}
