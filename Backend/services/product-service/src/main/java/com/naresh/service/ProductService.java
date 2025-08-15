package com.naresh.service;
import com.naresh.Repository.CategoryRepository;
import com.naresh.Repository.ProductRepository;
import com.naresh.dto.*;
import com.naresh.exception.CategoryNotFoundException;
import com.naresh.exception.ProductPurchaseException;
import com.naresh.mapper.CategoryMapper;
import com.naresh.mapper.ProductMapper;
import com.naresh.model.Category;
import com.naresh.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductMapper productMapper;
    public Category getCategory(String id){
        return categoryRepository.findById(id).orElseThrow(()->{
             return new CategoryNotFoundException("Category not found with the give ID: "+id);
         });
    }
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public Optional<ProductResponse> getProductById(String id){
        return Optional.ofNullable(productRepository.findById(id).map(productMapper::fromProduct).orElseThrow(() -> {
            return new ProductPurchaseException("Product not found with the give ID: " + id);
        }));
    }
    public List<ProductResponse> getAllProducts(){
      return productRepository.findAll().stream().map(productMapper::fromProduct).toList();
    }
    public List<ProductResponse>getProductByCategory(String id){
       if(id==null){
           return getAllProducts();
       }
        return productRepository.findAllByCategoryId(id);
    }
    public List<Category> createCategory(List<CategoryRequest> categoryRequest){
        List<Category> categoryList=new ArrayList<>();
        for(CategoryRequest cat: categoryRequest){
            Category category= categoryMapper.toCategory(cat);
        categoryList.add(category);
        }
       return categoryRepository.saveAll(categoryList);
    }

    public List<Product> addProductListToCategory(String id, List<ProductRequest> productRequestList){
        Category category=getCategory(id);
        List<Product> products=new ArrayList<>();
        for(ProductRequest req:productRequestList){
           Product product=productMapper.toProduct(req);
                   product.setCategory(category);

                   products.add(product);
        }
        return productRepository.saveAll(products);
    }
    public void DeleteCategory(String id){
        getCategory(id);
        categoryRepository.deleteById(id);
    }
    public void DeleteProduct(String id){
        productRepository.deleteById(id);
    }
    @Transactional()
    public List<PurchaseResponse>purchaseProduct(List<PurchaseRequest>request ){
        var productIds=request
                .stream().map(PurchaseRequest::productId )
                .toList();
        var storedProducts=productRepository.findAllByIdInOrderById(productIds);
        if(productIds.size()!= storedProducts.size()){
            throw new ProductPurchaseException("One or more product does not exist");
        }
        var sortedRequest=request.stream().sorted(
                Comparator.comparing(PurchaseRequest::productId)
        ).toList();
        var purchasedProduct=new ArrayList<PurchaseResponse>();
        for(int i=0;i< storedProducts.size();i++){
            var product=storedProducts.get(i);
            var productRequest=sortedRequest.get(i);
            if(product.getStock()< productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity");
            }
            var newstock=product.getStock()- productRequest.quantity();
            product.setStock(newstock);
            productRepository.save(product);
            purchasedProduct.add(productMapper.toPurchaseResponse(product, productRequest.quantity()));
        }
        return purchasedProduct;
    }
}
