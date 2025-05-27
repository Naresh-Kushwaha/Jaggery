package com.naresh.dto;

public record UserEntityResponse(
        Long id,
        String username,
        String email
) {
}
