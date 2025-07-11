package com.naresh.mapper;

import com.naresh.kafka.payment.PaymentConfirmation;
import com.naresh.kafka.payment.PaymentConfirmationDTO;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {


    public PaymentConfirmation toPaymentConfirmation(PaymentConfirmationDTO paymentConfirmationDTO){
        return  PaymentConfirmation.builder()
                        .orderReference(paymentConfirmationDTO.orderReference())
                                .email(paymentConfirmationDTO.email())
                                        .paymentMethod(paymentConfirmationDTO.paymentMethod())
                                                .amount(paymentConfirmationDTO.amount())
                .build();
    }
    public PaymentConfirmationDTO fromPaymentConfirmation(PaymentConfirmation paymentConfirmation){
        return new PaymentConfirmationDTO(paymentConfirmation.getOrderReference(),
                paymentConfirmation.getEmail(), paymentConfirmation.getPaymentMethod(),
                paymentConfirmation.getAmount());

    }
}
