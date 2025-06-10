package com.naresh.notification;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.naresh.kafka.order.OrderConfirmation;
//import com.naresh.kafka.payment.PaymentConfirmation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @JsonIgnore
    @OneToMany
    private List<OrderConfirmation> orderConfirmations;
//    @JsonIgnore
//    @OneToMany
////    private List<PaymentConfirmation>paymentConfirmations;
//
}
