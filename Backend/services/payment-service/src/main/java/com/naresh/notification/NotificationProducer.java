package com.naresh.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationProducer {
    private final KafkaTemplate<String,PaymentNotificationRequest>kafkaTemplat;

    public NotificationProducer(KafkaTemplate<String, PaymentNotificationRequest> kafkaTemplat) {
        this.kafkaTemplat = kafkaTemplat;
    }

    public void sendNotification(PaymentNotificationRequest request){
        log.info("Sending  notification with body =< {} >",request);
        Message<PaymentNotificationRequest> message= MessageBuilder
                .withPayload(request)
                .setHeader(TOPIC,"payment-topic")
                .build();
        kafkaTemplat.send(message);
    }

}
