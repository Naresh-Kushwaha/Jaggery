package com.naresh.notification;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.naresh.kafka.OrderConfirmation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
