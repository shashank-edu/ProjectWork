package com.webapp.springbootweb.Products.services;

import java.util.List;

import com.webapp.springbootweb.Products.ExceptionalHandeling.ProductNotFoundException;
import com.webapp.springbootweb.Products.entities.Product;
import com.webapp.springbootweb.Products.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository Prepo;

    // post method
    public Product saveProduct(Product prod){
        return Prepo.save(prod);
        // Product proCheck = Prepo.findById(prod.getId()).orElse(null);
        // if(proCheck != null){
        //     return  Prepo.save(prod);
        // }else{
        //     return null;
        // }
        // return Prepo.save(prod);
        // return Prepo.findById(prod.getId()).orElse(null);
        

    }

    public List<Product> saveProducts(List<Product> prod){
        return Prepo.saveAll(prod);

    }

    public List<Product> getProducts(){
        return Prepo.findAll();
        
    }

    public Product getProductsById(int id){
        Product product= Prepo.findById(id).orElse(null);
        if(product != null){
            return product;
        }
        throw new ProductNotFoundException("Could not find any Product with ID "+id);
    }
    

    public String deleteProduct(Integer id){
        Prepo.deleteById(id);
        return "Product removed !! "+id;
    }

    public Product updateProduct(Product product){
        Product existingProduct =Prepo.findById(product.getId()).orElse(null);
        if (existingProduct != null){
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setBatchNo(product.getBatchNo());
            existingProduct.setQuantity(product.getQuantity());
            return Prepo.save(existingProduct);
        }else{
            return null;
        }
        


    }

    public Product updateProductbyId(Product product,int id) {
        Product existingProduct = Prepo.findById(id).orElse(null);
        if (existingProduct != null){
            // Product existingProduct =Prepo.findById(id).orElse(null);
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setBatchNo(product.getBatchNo());
            existingProduct.setQuantity(product.getQuantity());
            return Prepo.save(existingProduct);
        }else{
            return null;
        }
        
    }

    // public Product CheckAvalability(int id) {
    //     Product product= Prepo.findById(id).orElse(null);
    //     if(product != null){
    //         return product;
    //     }
    //     throw new ProductNotFoundException("Could not find any Product with ID "+id);
    // }


}