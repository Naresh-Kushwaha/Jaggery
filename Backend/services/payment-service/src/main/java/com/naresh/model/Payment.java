package com.naresh.model;

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.naresh.dto.PaymentMethod;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@EntityListeners(AuditingEntityListener.class)
@Entity
@Data
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private BigDecimal amount;
    private Long orderId;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @CreatedDate
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

}
