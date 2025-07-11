package com.naresh.controller;

import com.naresh.email.EmailService;
import com.naresh.kafka.order.OrderConfirmation;
import com.naresh.kafka.order.OrderConfirmationDTO;
import com.naresh.kafka.payment.PaymentConfirmation;
import com.naresh.kafka.payment.PaymentConfirmationDTO;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {
    private final EmailService emailService;
    @PostMapping("/send")
    public ResponseEntity<String>send(@RequestBody OrderConfirmationDTO paymentConfirmationDTO) throws MessagingException {
        emailService.sendOrderConfirmation(paymentConfirmationDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
