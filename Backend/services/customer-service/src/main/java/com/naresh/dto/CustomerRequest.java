package com.naresh.dto;

import com.naresh.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CustomerRequest(
       @NotNull(message="Customer id cannot be null")
        Long id,
        @NotEmpty(message="Provide mobile")
        String mobile,
        @NotEmpty(message="Provide Email")
        @Email(message = "Enter Valid Email")
        String email,
        List<Address> address
) {
}
