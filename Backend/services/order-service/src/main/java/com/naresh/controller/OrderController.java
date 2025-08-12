package com.naresh.controller;

import com.naresh.dto.OrderRequest;
import com.naresh.dto.OrderResponse;
import com.naresh.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RefreshScope
@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

private final OrderService service;
int attempts=0;
//@CircuitBreaker(name="customerCB",fallbackMethod = "fallback")
//@Retry(name="customerCB",fallbackMethod = "fallback")
    @RateLimiter(name="customerCB",fallbackMethod = "fallback")
 @PostMapping("/create-order")
    public ResponseEntity<String> createOrder(@RequestBody @Valid OrderRequest request, @AuthenticationPrincipal Jwt jwt){
    System.out.println("Attempts "+ ++attempts);
     return  ResponseEntity.ok(service.createOrder(request,jwt));
 }

    public ResponseEntity<String> fallback(Exception e) {
        return ResponseEntity.ok("Fallback method");
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(this.service.findAllOrders());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable("order-id") Long orderId) {
        return ResponseEntity.ok(this.service.findById(orderId));
    }

}
