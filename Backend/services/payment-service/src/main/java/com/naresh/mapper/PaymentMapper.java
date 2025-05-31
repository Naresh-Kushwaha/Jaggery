package com.naresh.mapper;

import com.naresh.dto.PaymentRequest;
import com.naresh.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request){
        return Payment.builder().
                amount(request.amount())
                        .paymentMethod(request.paymentMethod())
                                .orderId(request.orderId())
                                        .
                build();
    }
}
