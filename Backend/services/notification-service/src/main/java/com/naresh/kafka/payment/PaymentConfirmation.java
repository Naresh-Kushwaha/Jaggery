package com.naresh.kafka.payment;

import com.naresh.notification.Notification;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
@Entity
@Data
public class PaymentConfirmation{
       @Id
      private String orderReference;
       private  BigDecimal amount;
       private   PaymentMethod paymentMethod;
      private String email;
      @ManyToOne
    private Notification notification;


}
