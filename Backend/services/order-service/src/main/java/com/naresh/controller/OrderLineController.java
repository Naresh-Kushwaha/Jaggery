package com.naresh.controller;

import com.naresh.dto.OrderLineResponse;
import com.naresh.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order/order-lines")
public class OrderLineController {
    private final OrderLineService service;

    @GetMapping("/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(
            @PathVariable("order-id") Long orderId
    ) {
        return ResponseEntity.ok(service.findAllByOrderId(orderId));
    }
}
