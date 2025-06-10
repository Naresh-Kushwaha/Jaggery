package com.naresh.kafka.payment;
import com.naresh.email.EmailService;
import com.naresh.notification.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationRepository repository;
    private final EmailService emailService;


    @KafkaListener(topics = "payment-topic",groupId = "notificationGroup")
    public void consumePaymentSuccessNotification(String paymentRequest) {
      System.out.println("Consuming the message from payment-topic");


    }
}
