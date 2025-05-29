package com.naresh.customer;

public record CustomerResponse(
        Long id,
        String username,
        String email
) {
}
