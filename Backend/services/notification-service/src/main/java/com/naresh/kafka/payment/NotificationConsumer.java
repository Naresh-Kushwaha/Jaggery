package com.naresh.kafka.payment;

import com.naresh.email.EmailService;
import com.naresh.notification.Notification;
import com.naresh.notification.NotificationRepository;
import com.naresh.notification.NotificationType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {
    private final NotificationRepository repository;
    private final EmailService emailService;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfiramtion) throws MessagingException {
        log.info("Consuming the message from payment-topic");
        repository.save(Notification.builder()
                .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfiramtion)
                        .build()
                );
        var customerName=paymentConfiramtion.customerName;
        emailService.send();
    }
}
