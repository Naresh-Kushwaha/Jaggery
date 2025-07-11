package com.naresh.controller;

import com.naresh.dto.OrderRequest;
import com.naresh.dto.OrderResponse;
import com.naresh.service.OrderLineService;
import com.naresh.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

private final OrderService service;
 @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody @Valid OrderRequest request, Principal principal){
     return  ResponseEntity.ok(service.createOrder(request,principal));
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
