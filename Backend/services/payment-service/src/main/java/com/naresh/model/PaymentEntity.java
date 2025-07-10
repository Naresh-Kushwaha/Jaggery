package com.naresh.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@EntityListeners(AuditingEntityListener.class)
@Entity
@Data
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;
    private Long amount;
    private String currency;
    private String status;
    private String userEmail;
    private String method;
    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime created;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updated;


}
