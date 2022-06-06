package com.AlgoProject.SellerService.Services;

import com.AlgoProject.SellerService.Entities.Product;
import com.AlgoProject.SellerService.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public String deleteProductById(Integer id){
        productRepository.deleteById(id);
        return "Product removed !! "+id;
    }

    public Product updateProductbyId(Product product,int id) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null){
            // Product existingProduct =productRepository.findById(id).orElse(null);
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setType(product.getType());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setRam(product.getRam());
            existingProduct.setProcessor(product.getProcessor());
            existingProduct.setColor(product.getColor());
            existingProduct.setMaterial(product.getMaterial());
            existingProduct.setSize(product.getSize());
            existingProduct.setFabric(product.getFabric());
            existingProduct.setPattern(product.getPattern());
            existingProduct.setGear(product.getGear());
            existingProduct.setTire_Size(product.getTire_Size());
            existingProduct.setWeight(product.getWeight());
            existingProduct.setSuspension(product.getSuspension());
            return productRepository.save(existingProduct);
        }else{
            return null;
        }

    }

    public  Product getProductByName(String name){
        return  productRepository.findItemByName(name);
    }



    public List<Product> getProductsListType(String type) {
        return productRepository.findAllType(type);
    }

    public List<Product> getProductsByRange(float min, float max, int sellerId) {
        return productRepository.findByProductsBetween(min,max,sellerId);
    }

    public List<Product> getProductsListCategory(String category) {
        return productRepository.findAllCategory(category);
    }

    public Product addproduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public List<Product> addproductList(List<Product> products) {
        productRepository.saveAll(products);
        return products;
    }



    public List<Product> getProductBysellerId(int sellerId) {
       return productRepository.getProductBysellerId(sellerId);
    }
}