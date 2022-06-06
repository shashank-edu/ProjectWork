package com.AlgoProject.SellerService.Entities;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    private String type;
    private String name;
    private int ram;
    private String processor;
    private int price;
    private String color;
    private String material;
    private int size;
    private String Fabric;
    private String Pattern;
    private String Gear;
    private float Tire_Size;
    private float weight;
    private Double Frame_size;
    private String Suspension;
    private int seller_id;

}
