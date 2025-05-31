package com.naresh.service;

import com.naresh.dto.PaymentRequest;
import com.naresh.mapper.PaymentMapper;
import com.naresh.notification.NotificationProducer;
import com.naresh.notification.PaymentNotificationRequest;
import com.naresh.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;
private final NotificationProducer notificationProducer;
    public Long createPayment(PaymentRequest request){
        var payment=this.repository.save(mapper.toPayment(request));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customerName(),
                        request.email()
                )
        );
        return payment.getId();
    }



}
