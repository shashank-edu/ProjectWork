package com.springboot.product.Repository;

import com.springboot.product.Entities.Product;
import org.springframework.beans.factory.annotation.Configurable;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

@Configurable
public interface ProductRepository extends MongoRepository<Product, Integer> {

    // public Product findByName(String Name);
    @Query(value = "{'type':?0}")
    public List<Product> findByType(String type);

    @Query("{name:'?0'}")
    Product findByName(String name);

    @Query(value = "{'category':?0}")
    List<Product> findByCategory(String category);

    @Query("{$and:[{'price':{$gte:?0}},{'price':{$lte:?1}}]}")
    public List<Product> findProductByPriceRange(int min, int max);
}