package com.naresh.config;

import com.naresh.Utils;
import com.naresh.dto.RazorpayVerifyRequest;
import com.naresh.service.RazorpayService;
import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/payment/razorpay")
public class RazorpayVerificationController {

    @Value("${razorpay.key_secret}")
    private String keySecret;
    @Value("${razorpay.key_id}")
    private String keyId;
   @Autowired
    private RazorpayService razorpayService;

    @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestParam int amount) {
        return ResponseEntity.ok(razorpayService.createOrder(amount));
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyPayment(@RequestBody RazorpayVerifyRequest request) {
        return razorpayService.verifyPayment(request);

    }

}
