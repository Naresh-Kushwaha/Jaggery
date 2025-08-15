package com.naresh.Repository;

import com.naresh.model.CartItem;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends MongoRepository<CartItem,String> {
Optional< CartItem>  findByUserId(UUID userId);
}
