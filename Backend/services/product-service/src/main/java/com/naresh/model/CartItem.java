package com.naresh.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Document
@Data
public class CartItem {
    @Id
    private String id;
    private UUID userId;
    private String email;
    private List<Product> products=new ArrayList<>();

}
