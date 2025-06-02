package com.naresh.kafka.payment.order;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class Product{
        Long productId;
        String name;
        String description;
        BigDecimal price;
        double quantity;

}
