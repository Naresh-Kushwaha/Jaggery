package com.naresh.mapper;

import com.naresh.dto.ProductRequest;
import com.naresh.dto.ProductResponse;
import com.naresh.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(ProductRequest productRequest){
        return Product.builder()
                .price(productRequest.price())
                .name(productRequest.name())
                .availableQuantity(productRequest.availableQuantity())
                .description(productRequest.description())
                .build();
    }
    public ProductResponse fromProduct(Product product){
        return new ProductResponse(
                product.getName(), product.getAvailableQuantity()
        );
    }
}
