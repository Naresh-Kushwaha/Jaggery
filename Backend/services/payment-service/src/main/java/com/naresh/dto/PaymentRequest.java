package com.naresh.dto;

public record PaymentRequest(
     Long amount,
     String orderReference,
     String email

) {
}
