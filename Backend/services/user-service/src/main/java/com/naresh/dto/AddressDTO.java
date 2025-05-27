package com.naresh.dto;

public record AddressDTO(
         Long id,
         String houseNo,
         String street,
         String landmark,
        String district,
         String state,
        String country,
         String pinCode
) {
}
