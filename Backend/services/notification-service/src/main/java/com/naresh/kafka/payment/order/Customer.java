package com.naresh.kafka.payment.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Customer{
        Long id;
        String name;
        String email;

}
