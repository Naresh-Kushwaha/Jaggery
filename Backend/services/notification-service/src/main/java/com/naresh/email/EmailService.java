package com.naresh.email;
import com.naresh.kafka.order.OrderConfirmation;
import com.naresh.kafka.order.OrderConfirmationDTO;
import com.naresh.kafka.order.OrderNotificationRepository;
import com.naresh.kafka.order.PurchaseResponse;
import com.naresh.kafka.payment.PaymentConfirmation;
import com.naresh.kafka.payment.PaymentConfirmationDTO;
import com.naresh.kafka.payment.PaymentNotificationRepository;
import com.naresh.mapper.OrderMapper;
import com.naresh.mapper.PaymentMapper;
import com.naresh.notification.Notification;
import com.naresh.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.Store;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private final NotificationRepository notificationRepository;
    private final PaymentMapper paymentMapper;
    private final OrderMapper orderMapper;
    private final PaymentNotificationRepository paymentNotificationRepository;
    private final OrderNotificationRepository orderNotificationRepository;
     @Value("${email.setFrom}")
     private String setFrom;
    @Transactional
    public void sendOrderConfirmation(OrderConfirmationDTO orderConfirmationDTO) {

//        OrderConfirmation orderConfirmation = orderMapper.toOrderConfirmation(orderConfirmationDTO);
        OrderConfirmation orderConfirmation=new OrderConfirmation();
        orderConfirmation.setEmail(orderConfirmationDTO.email());
        orderConfirmation.setOrderReference(orderConfirmationDTO.orderReference());
        orderConfirmation.setTotalAmount(orderConfirmationDTO.totalAmount());
        List<PurchaseResponse> prList=new ArrayList<>();
for(PurchaseResponse pr:orderConfirmationDTO.products()){
    PurchaseResponse purchase=new PurchaseResponse();
    purchase.setOrderConfirmation(orderConfirmation);
    purchase.setName(pr.getName());
    purchase.setPrice(pr.getPrice());
    purchase.setQuantity(pr.getQuantity());
    purchase.setDescription(pr.getDescription());
    prList.add(purchase);

}
orderConfirmation.setProducts(prList);
        Optional<Notification> notificationOpt =
                notificationRepository.findByEmail(orderConfirmationDTO.email());
        if (notificationOpt.isPresent()) {
            Notification storedNotification = notificationOpt.get();

            orderConfirmation.setNotification(storedNotification);
            storedNotification.getOrderConfirmations().add(orderConfirmation);
            notificationRepository.save(storedNotification);
        } else {
            Notification newNotification = new Notification();
            newNotification.setEmail(orderConfirmation.getEmail());

            // IMPORTANT: set the back-reference
            orderConfirmation.setNotification(newNotification);
            newNotification.getOrderConfirmations().add(orderConfirmation);

            notificationRepository.save(newNotification);
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(setFrom);
        message.setTo(orderConfirmation.getEmail());
        message.setSubject("Order Placed Successfully");
        message.setText("Products: " + orderConfirmation.getTotalAmount());
        mailSender.send(message);
    }

    @Transactional
    public void sendPaymentConfirmation(PaymentConfirmationDTO paymentConfirmationDTO) {
        PaymentConfirmation paymentConfirmation = paymentMapper.toPaymentConfirmation(paymentConfirmationDTO);
          Optional<Notification> notification=notificationRepository.findByEmail(paymentConfirmation.getEmail());
          if(notification.isPresent()){
              Notification StoredNotification=notification.get();
              StoredNotification.getPaymentConfirmations().add(paymentConfirmation);
              notificationRepository.save(StoredNotification);
          }
          else {
              Notification newNotification=new Notification();
              newNotification.setEmail(paymentConfirmation.getEmail());
              newNotification.getPaymentConfirmations().add(paymentConfirmation);
              notificationRepository.save(newNotification);
          }
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(setFrom);
        message.setTo(paymentConfirmationDTO.email());
        message.setSubject("Payment Successful");
        message.setText("Total Amount Paid: " + paymentConfirmationDTO.amount());
        mailSender.send(message);
    }

}
