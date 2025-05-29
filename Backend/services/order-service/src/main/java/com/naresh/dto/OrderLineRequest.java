package com.naresh.dto;

public record OrderLineRequest(

        Long orderId,
        @jakarta.validation.constraints.NotNull(message = "Product is mandatory") Long productId,
        double quantity
) {
}
