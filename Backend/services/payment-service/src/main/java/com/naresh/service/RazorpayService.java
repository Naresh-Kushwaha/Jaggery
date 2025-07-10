package com.naresh.service;



import com.naresh.Utils;
import com.naresh.dto.PaymentRequest;
import com.naresh.dto.RazorpayVerifyRequest;
import com.naresh.model.PaymentEntity;
import com.naresh.repository.PaymentRepository;
import com.razorpay.Order;

import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class RazorpayService {
private final PaymentRepository paymentRepository;

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    public String createOrder(PaymentRequest paymentRequest) {
        try {
            RazorpayClient razorpay = new RazorpayClient(keyId, keySecret);

            JSONObject options = new JSONObject();
            options.put("amount", paymentRequest.amount()); // amount in paise
            options.put("currency", "INR");
            options.put("receipt", "txn_" + System.currentTimeMillis());
            options.put("payment_capture", 1);

            Order order = razorpay.orders.create(options);
          PaymentEntity payment=new PaymentEntity();
          payment.setRazorpayOrderId(order.get("id"));


          payment.setStatus("CREATED");

          paymentRepository.save(payment);
          return order.toString();

        } catch (Exception e) {
            throw new RuntimeException("Failed to create Razorpay order", e);
        }
    }

    public boolean verifyPayment(RazorpayVerifyRequest request) throws Exception {
        System.out.println(request);
      PaymentEntity paymentEntity=paymentRepository.findByRazorpayOrderId(request.getRazorpay_order_id())
              .orElseThrow(()->new RuntimeException("Payment not found"));
            RazorpayClient razorpay = new RazorpayClient(keyId, keySecret);
            String paymentId = request.getRazorpay_payment_id();
            String orderId = request.getRazorpay_order_id();
            String signature = request.getRazorpay_signature();

            String generatedSignature = Utils.calculateRazorpaySignature(
                    orderId + "|" + paymentId,
                    keySecret
            );
            System.out.println(generatedSignature);

            if (generatedSignature.equals(signature)) {
                JSONObject payment = razorpay.payments.fetch(paymentId).toJson();
                System.out.println(payment);

                paymentEntity.setRazorpayPaymentId(paymentId);
                paymentEntity.setRazorpaySignature(signature);

                // amount may be Integer or Long depending on JSON parser
                if (payment.has("amount")) {
                    paymentEntity.setAmount(
                            ((Number) payment.get("amount")).longValue()/100
                    );
                }
                paymentEntity.setCugitrrency(payment.optString("currency",null));
                paymentEntity.setMethod(
                        payment.optString("method", null)
                );
                paymentEntity.setStatus(
                        payment.optString("status", null)
                );
                paymentEntity.setUserEmail(
                        payment.optString("email", null)
                );
                paymentRepository.save(paymentEntity);
                return true;
            } else {
                paymentEntity.setStatus("FAILED");
                paymentRepository.save(paymentEntity);
                return false;

        }
    }
}
