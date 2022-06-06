package com.AlgoProject.SellerService.Repository;

import com.AlgoProject.SellerService.Entities.Product;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


@Configurable
public interface ProductRepository extends JpaRepository<Product,Integer>{

    @Query(value="SELECT * FROM product WHERE name = ?1", nativeQuery = true)
    Product findItemByName(String name);

    @Query(value="SELECT * FROM product WHERE price between ?1 and ?2 and seller_id=?3", nativeQuery = true)
    List<Product> findByProductsBetween(float min, float max, int sellerId);

    @Query(value="SELECT * FROM product WHERE type =?1", nativeQuery = true)
    List<Product> findAllType(String type);

    @Query(value="SELECT * FROM product WHERE category =?1", nativeQuery = true)
    List<Product> findAllCategory(String category);

    @Query(value="SELECT * FROM product WHERE seller_id =?1", nativeQuery = true)
    List<Product> getProductBysellerId(int sellerId);
}