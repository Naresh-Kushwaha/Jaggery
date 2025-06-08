package com.naresh.kafka.order;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Embeddable
@Data
public class Customer{
        Long id;
        String name;
        String email;

}
