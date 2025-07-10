package com.naresh.controller;

import com.naresh.dto.PaymentRequest;
import com.naresh.dto.RazorpayVerifyRequest;

import com.naresh.service.RazorpayService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payment")
public class PaymentController {

    private final RazorpayService service;

    @PostMapping("/create-order")
    ResponseEntity<String> requestOrderPayment(
            @RequestBody @Valid PaymentRequest request
    ) {
        try {
            return ResponseEntity.ok(service.createOrder(request));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody RazorpayVerifyRequest request) throws Exception {

            boolean verified = service.verifyPayment(request);
            return ResponseEntity.ok(Map.of("Success", verified));

    }
}