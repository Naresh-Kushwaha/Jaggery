package com.naresh.auth_service.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
