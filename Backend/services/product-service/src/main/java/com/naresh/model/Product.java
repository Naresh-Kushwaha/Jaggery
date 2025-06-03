package com.naresh.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String image;
   private String description;
   private double stock;
   private BigDecimal price;
   private String ingredients;
   @ManyToOne
   @JoinColumn(name="category_id")
   private Category category;
}
