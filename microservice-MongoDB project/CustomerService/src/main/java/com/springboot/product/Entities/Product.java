package com.springboot.product.Entities;

import java.util.Map;

import javax.persistence.*;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    private String type;
    private String name;
    private int seller_id;
    private int price;
    private Map<String, String> product_list;

    public Product() {
    }

    public Product(int id, String category, String type, String name, int seller_id, int price,
            Map<String, String> product_list) {
        this.id = id;
        this.category = category;
        this.type = type;
        this.name = name;
        this.seller_id = seller_id;
        this.price = price;
        this.product_list = product_list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Map<String, String> getProduct_list() {
        return product_list;
    }

    public void setProduct_list(Map<String, String> product_list) {
        this.product_list = product_list;
    }

}
