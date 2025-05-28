package com.naresh.dto;

public record CustomerResponse(
        Long id,
        String username,
        String email
) {
}
