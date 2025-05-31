package com.naresh.controller;

import com.naresh.email.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/notification")
public class EmailController {
   private final EmailService emailService;
   @PostMapping("/sendEmail")
    public String sendEmail() throws MessagingException {
       emailService.send();
       return "Email Sent";
   }
}
