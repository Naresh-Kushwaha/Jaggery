package com.naresh.controller;

import com.naresh.dto.*;
import com.naresh.model.Category;
import com.naresh.model.Product;
import com.naresh.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RefreshScope
@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping()
    public ResponseEntity<Optional<ProductResponse>> getProductById(@RequestParam("productId") String id){
        return ResponseEntity.ok(service.getProductById(id));
    }
    @GetMapping("/getCategory")
     public ResponseEntity<Category> getCategory(@RequestParam("categoryId") String id){
         return ResponseEntity.status(HttpStatus.OK).body(service.getCategory(id));
     }
    @GetMapping("/getAllCategory")
    public ResponseEntity<List<Category>> getAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllCategory());
    }
     @GetMapping ("/getProductByCategoryId")
     public ResponseEntity<List<ProductResponse>>getProductByCategoryId(@RequestParam("categoryId")String id){
        return ResponseEntity.ok(service.getProductByCategory(id));
     }
     @GetMapping("/getAllProducts")
     public ResponseEntity<List<ProductResponse>>getAllProduct(){
         return ResponseEntity.ok(service.getAllProducts());
     }
     @PostMapping("/createCategory")
    public ResponseEntity<List<Category>> createCategory(@RequestBody List<CategoryRequest> categoryRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCategory(categoryRequest));
     }
    @PostMapping("/addProductListToCategory")
    public ResponseEntity<List<Product>> addProductListToCategory(@RequestParam("categoryId") String id,
                                                                  @RequestBody  List<ProductRequest> productRequestList) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.addProductListToCategory(id, productRequestList));
    }
    @PostMapping("/purchase")
    public ResponseEntity<List<PurchaseResponse>> purchaseProduct(
            @RequestBody List<PurchaseRequest>req
    ) {
        return ResponseEntity.ok(service.purchaseProduct(req));
    }
    @DeleteMapping("/deleteCategory")
    public ResponseEntity deleteCategory(@RequestParam("category_id") String id){
        service.DeleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/deleteProduct")
    public ResponseEntity deleteProdcut(@RequestParam("category_id") String id){
        service.DeleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

