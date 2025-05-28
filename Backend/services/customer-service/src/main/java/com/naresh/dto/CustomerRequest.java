package com.naresh.dto;

import com.naresh.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CustomerRequest(
        @NotEmpty(message="Provide UserName")
        String username,
        @NotEmpty(message="Provide Password")
        String password,
        @NotEmpty(message="Provide mobile")
        String mobile,
        @NotEmpty(message="Provide Email")
        @Email(message = "Enter Valid Email")
        String email,
       List<Address> address
) {
}
