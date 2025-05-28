package com.naresh.service;

import com.naresh.Repository.CategoryRepository;
import com.naresh.Repository.ProductRepository;
import com.naresh.dto.CategoryRequest;
import com.naresh.dto.CategoryResponse;
import com.naresh.dto.ProductRequest;
import com.naresh.dto.ProductResponse;
import com.naresh.exception.CategoryNotFoundException;
import com.naresh.exception.ProductNotFoundException;
import com.naresh.mapper.CategoryMapper;
import com.naresh.mapper.ProductMapper;
import com.naresh.model.Category;
import com.naresh.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;
    public Category getCategory(Long id){
        return categoryRepository.findById(id).orElseThrow(()->{
             return new CategoryNotFoundException("Category not found with the give ID: "+id);
         });
    }
    public Product getProduct(Long id){
        return productRepository.findById(id).orElseThrow(()->{
            return new ProductNotFoundException("Product not found with the give ID: "+id);
        });
    }
    public Category createCategory(CategoryRequest categoryRequest){
        Category category= categoryMapper.toCategory(categoryRequest);
       return categoryRepository.save(category);
    }
    public ProductResponse addProductToCategory(Long id, ProductRequest productRequest){
        Category category=getCategory(id);
        Product  product=productMapper.toProduct(productRequest);
        product.setCategory(category);
        category.getProductList().add(product);
        categoryRepository.save(category);
        return productMapper.fromProduct(product);
    }
    public List<ProductResponse> addProductListToCategory(Long id, List<ProductRequest> productRequestList){
        Category category=getCategory(id);
        List<ProductResponse> productresponse=new ArrayList<>();
        for(ProductRequest req:productRequestList){
           Product product=productMapper.toProduct(req);
                   product.setCategory(category);
                   category.getProductList().add(product);
                   productresponse.add(productMapper.fromProduct(product));
        }
        categoryRepository.save(category);

        return productresponse;
    }
    public void DeleteCategory(Long id){
        getCategory(id);
        categoryRepository.deleteById(id);
    }
    public void DeleteProduct(Long id){
        getProduct(id);
        productRepository.deleteById(id);
    }
}
