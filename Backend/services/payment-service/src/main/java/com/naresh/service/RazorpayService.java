package com.naresh.service;



import com.naresh.Utils;
import com.naresh.dto.RazorpayVerifyRequest;
import com.razorpay.Order;

import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RazorpayService {

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    public String createOrder(int amountInRupees) {
        try {
            RazorpayClient razorpay = new RazorpayClient(keyId, keySecret);

            JSONObject options = new JSONObject();
            options.put("amount", amountInRupees * 100); // amount in paise
            options.put("currency", "INR");
            options.put("receipt", "txn_" + System.currentTimeMillis());
            options.put("payment_capture", 1);

            Order order = razorpay.orders.create(options);

            return order.toString();

        } catch (Exception e) {
            throw new RuntimeException("Failed to create Razorpay order", e);
        }
    }

    public ResponseEntity<String> verifyPayment(RazorpayVerifyRequest request) {
        try {
            RazorpayClient razorpay = new RazorpayClient(keyId, keySecret);
            String paymentId = request.getRazorpay_payment_id();
            String orderId = request.getRazorpay_order_id();
            String signature = request.getRazorpay_signature();

            System.out.println("orderId = " + orderId);
            System.out.println("paymentId = " + paymentId);
            System.out.println("signature = " + signature);

            String generatedSignature = Utils.calculateRazorpaySignature(
                    orderId + "|" + paymentId,
                    keySecret
            );

            System.out.println("generatedSignature = " + generatedSignature);

            if (generatedSignature.equals(signature)) {
                return ResponseEntity.ok("Payment verified");
            } else {
                return ResponseEntity.status(400).body("Invalid signature");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error verifying payment");
        }
    }
}
