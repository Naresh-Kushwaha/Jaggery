package com.naresh.service;

import com.naresh.Repository.CartRepository;
import com.naresh.Repository.ProductRepository;
import com.naresh.model.CartItem;
import com.naresh.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public String addToCart(String productId, Jwt jwt) {
        Map<String,Object>claims=jwt.getClaims();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        UUID userId=UUID.fromString((String) claims.get("sub"));
        CartItem cartItem = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    CartItem newCart = new CartItem();
                    newCart.setUserId(userId);
                    return newCart;
                });

        if (!cartItem.getProducts().contains(product)) {
            cartItem.getProducts().add(product);
        }
        cartRepository.save(cartItem); // saves new or updated cart
        return "Product added to cart";
    }
    public List<Product> getCart(Jwt jwt){
        Map<String,Object>claims=jwt.getClaims();
        UUID userId=UUID.fromString((String) claims.get("sub"));
        return cartRepository.findByUserId(userId).orElseThrow(()->
        new RuntimeException("cart not found")).getProducts();

    }
    public void removeFromCart(String id,Jwt jwt){
        Map<String,Object>claims=jwt.getClaims();
        UUID userId=UUID.fromString((String) claims.get("sub"));
    CartItem cartItem=cartRepository.findByUserId(userId).orElseThrow(()->
        new RuntimeException("Cart NotFound")
    );
 boolean removed=cartItem.getProducts().removeIf(
         product -> product.getId().equals(id)
 );
 if(removed){
     cartRepository.save(cartItem);
 }else{
     throw new RuntimeException("Product not found in cart ");
 }

    }
}