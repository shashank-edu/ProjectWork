package com.webapp.springbootweb.Products.repositories;

import com.webapp.springbootweb.Products.entities.Product;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;


@Configurable
public interface ProductRepository extends JpaRepository<Product,Integer> {

    // Product findbyName(String name);
    
}