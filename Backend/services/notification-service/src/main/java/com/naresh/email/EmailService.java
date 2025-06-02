package com.naresh.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.UTF8;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static com.naresh.notification.NotificationType.PAYMENT_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void send() throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nn9619807@gmail.com");
        message.setTo("nareshkushwaha0307@gmail.com");
        message.setSubject("Testing Email");
        message.setText("hiii");
         // your configured email

        mailSender.send(message);

    }
}
