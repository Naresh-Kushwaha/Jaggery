package com.naresh.dto;

public record OrderLineRequest(

        Integer orderId,
        Integer productId,
        double quantity
) {
}
