package com.naresh.dto;

import com.naresh.model.Address;

import java.util.List;

public record UserEntityRequest(
        String username,
        String password,
        String mobile,
        String email,
       List<Address> address
) {
}
