package com.naresh.dto;

import com.naresh.model.Address;

import java.util.List;

public record CustomerResponse(
        String mobile,
        String email,
        List<Address> address
) {
}
