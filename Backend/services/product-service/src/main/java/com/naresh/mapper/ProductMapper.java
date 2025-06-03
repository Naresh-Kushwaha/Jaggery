package com.naresh.mapper;

import com.naresh.dto.ProductRequest;
import com.naresh.dto.ProductResponse;
import com.naresh.dto.PurchaseResponse;
import com.naresh.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(ProductRequest productRequest){
        return Product.builder()
                .price(productRequest.price())
                .name(productRequest.name())
                .stock(productRequest.stock())
                .description(productRequest.description())
                .build();
    }
    public ProductResponse fromProduct(Product product){
        return new ProductResponse(
                product.getName(), product.getstock()
        );
    }
    public PurchaseResponse toPurchaseResponse(Product product,double quantity){
        return  new PurchaseResponse(
                product.getId(), product.getName(), product.getDescription(), product.getPrice(),quantity
        );
    }
}
