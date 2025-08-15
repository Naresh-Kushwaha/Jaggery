package com.naresh.Repository;
import com.naresh.dto.ProductResponse;
import com.naresh.model.Product;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface ProductRepository extends MongoRepository<Product,String> {
    List<Product> findAllByIdInOrderById(List<Long> ids);
//    @Query("SELECT p FROM Product p WHERE p.category.id=:id ")
//    List<ProductResponse> findAllByCategoryId(Long id);
@Query("{ 'category.id' : ?0 }")
List<ProductResponse> findAllByCategoryId(String categoryId);
}
