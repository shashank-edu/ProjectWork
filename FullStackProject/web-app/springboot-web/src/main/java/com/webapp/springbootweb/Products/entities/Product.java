package com.webapp.springbootweb.Products.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 10)
    private String batchNo;
    
    @Column(nullable = false, length = 3)
    private int Quantity;

    @Column(nullable = false, unique = true, length = 45)
    private String name;

    @Column(nullable = false)
    private int price;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice (int price) {
        this.price = price;
    }

    public Product() {
    }
    public String getBatchNo() {
        return batchNo;
    }
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
    public int getQuantity() {
        return Quantity;
    }
   
    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Product(Integer id, String batchNo, int quantity, String name, int price) {
        this.id = id;
        this.batchNo = batchNo;
        this.Quantity = quantity;
        this.name = name;
        this.price = price;
    }
}
