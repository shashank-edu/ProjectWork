package com.springboot.product.Controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.springboot.product.Entities.Product;
import com.springboot.product.ExceptionalHandelling.ProductErrorResponse;
import com.springboot.product.ExceptionalHandelling.ProductNotFoundException;
import com.springboot.product.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.IsArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
// @Controller
// @ResponseBody
// @Transactional
// @CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/customer")
public class CustomerController {
    private static final Logger logger = LogManager.getLogger(CustomerController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/products")
    public List<Product> findAllProduct(Model model,
            RedirectAttributes redirectAttribute) {
        // Returning product by Name
        // logger.error("Getting Product by id", id);
        List<Product> product = productService.getAllProduct();
        // model.addAttribute("product", product);
        // model.addAttribute("pageTitle", "Edit Product (Name: " + name + ")");
        logger.info("Getting Product by Name", product);
        return product;
    }

    @GetMapping("/product")
    public Optional<Product> findProductById(@RequestParam int id, Model model,
            RedirectAttributes redirectAttribute) {
        // Returning product by Name
        logger.error("Getting Product by id", id);
        Optional<Product> product = productService.getProductById(id);
        // model.addAttribute("product", product);
        // model.addAttribute("pageTitle", "Edit Product (Name: " + name + ")");
        logger.info("Getting Product by Name", product);
        return product;
    }

    @GetMapping("/product/search")
    public List<Product> findProductByPriceRange(@RequestParam int min, @RequestParam int max, Model model,
            RedirectAttributes redirectAttribute) {
        // Returning product by Name
        logger.error("Getting Product by range", min, max);
        List<Product> product = productService.getProductByPriceRange(min, max);
        // model.addAttribute("product", product);
        // model.addAttribute("pageTitle", "Edit Product (Name: " + name + ")");
        logger.info("Getting Product by Name", product);
        return product;
    }

    @GetMapping("/product/type/{type}")
    public List<Product> findProductByType(@PathVariable String type, Model model,
            RedirectAttributes redirectAttribute) {
        // Returning product by Name
        List<Product> product = productService.getProductsListType(type);
        // model.addAttribute("product", product);
        // model.addAttribute("pageTitle", "Edit Product (Name: " + name + ")");
        logger.info("Getting Product by Type", product);
        return product;
    }

    @GetMapping("/product/category/{category}")
    public List<Product> findProductBycategory(@PathVariable String category,
            Model model,
            RedirectAttributes redirectAttribute) {
        // Returning product by Name
        List<Product> product = productService.getProductsListCategory(category);
        // model.addAttribute("product", product);
        // model.addAttribute("pageTitle", "Edit Product (Name: " + name + ")");
        logger.info("Getting Product by category", product);
        return product;
    }

    @PostMapping("/product/save")
    public Optional<Product> addProduct(@RequestBody Product product, RedirectAttributes redirectAttribute) {
        productService.saveProduct(product);
        System.out.println(product);
        redirectAttribute.addFlashAttribute("message", "The Product has been saved successfully.");
        // return "The Product has been saved successfully.";
        return productService.getProductById(product.getId());
    }

    @PutMapping("/product/edit/{id}")
    public Optional<Product> updateFormById(@RequestBody Product product,@PathVariable("id") Integer id,Model model){

    if (!productService.CheckAvalability(id)){
    throw new ProductNotFoundException("Student id Not Found - "+id);
    }
    Optional<Product> productResponse= productService.updateProductById(id,product);
    // return new ResponseEntity<Product>(prop, prop!=null ? HttpStatus.FOUND :
    // HttpStatus.NOT_FOUND);
    model.addAttribute("Listproduct", productResponse);
    return productResponse;
    }

    // updateProduct
    // @PreAuthorize("hasRole('ADMIN')")
    // @PutMapping("/product")
    // public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
    // Product prop = productService.updateProductById(product);
    // return new ResponseEntity<Product>(prop, prop != null ? HttpStatus.ACCEPTED :
    // HttpStatus.NOT_FOUND);
    // }

    // @GetMapping("/products")
    // public List<Product> findAllProducts(Model model) {
    // // return all the products
    // List<Product> Listproduct = productService.getProducts();
    // model.addAttribute("Listproduct", Listproduct);
    // logger.info("Fetching List of Products",Listproduct);
    // return Listproduct;
    // }
    //
    // @GetMapping("/product/id={id}")
    // public Product findProductById(@PathVariable int id, Model model,
    // RedirectAttributes redirectAttribute) {
    // // returning product by ID
    // Product product = productService.getProductsById(id);
    // model.addAttribute("product", product);
    // model.addAttribute("pageTitle", "Edit Product (ID: " + id + ")");
    // return product;
    // }
    //

    // @GetMapping("/product/delete/{id}")
    // public String deleteProductById(@PathVariable int id, RedirectAttributes
    // redirectAttribute) {
    // try {
    // productService.deleteProduct(id);
    // redirectAttribute.addFlashAttribute("message", "Product ID- " + id + "
    // Successfully deleted!");
    // } catch (ProductNotFoundException e) {
    // redirectAttribute.addFlashAttribute("message", e.getMessage());
    // }
    // return "redirect:/product";
    // }
    //
    // @GetMapping("/product/new")
    // public String ShowAddNewPage(Model model) {
    // model.addAttribute("product", new Product());
    // model.addAttribute("pageTitle", "Add new Product");
    // return "Addnew";
    // }
    //
    // // add an exception handler using @ExceptionalHandler
    // @ExceptionHandler
    // public String handleException(ProductNotFoundException exe) {
    //
    // // create a ProductErrorResponce
    // ProductErrorResponse error = new ProductErrorResponse();
    // error.setMessage(exe.getMessage());
    // error.setStatus(HttpStatus.NOT_FOUND.value());
    // error.setTimestamp(new Timestamp(System.currentTimeMillis()));
    // return "redirect:/product";
    // }
    //
    //
    // // updateProduct
    // // @PreAuthorize("hasRole('ADMIN')")
    // @PutMapping("/product")
    // public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
    // Product prop = productService.updateProduct(product);
    // return new ResponseEntity<Product>(prop, prop != null ? HttpStatus.ACCEPTED :
    // HttpStatus.NOT_FOUND);
    // }
    //
    // // updateProductbyId
    // @PutMapping("/product/edit/{id}")
    // public String updateProduct(@PathVariable int id, @RequestBody Product
    // product) {
    // Product prop = productService.updateProductbyId(product, id);
    // // return new ResponseEntity<Product>(prop, prop != null ?
    // HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND);
    // return "Successfully Update";
    // }
    //
    // @DeleteMapping("/product/{id}")
    // public String deleteProduct(@PathVariable Integer id) {
    //
    // if (productService.getProductsById(id) != null) {
    // return productService.deleteProduct(id);
    // } else {
    // return "product not found";
    // }
    //
    // // return productService.deleteProduct(id);
    // }
    //
    //
    // @PostMapping("/product/save")
    // public String addProduct(@RequestBody Product product, RedirectAttributes
    // redirectAttribute) {
    // productService.saveProduct(product);
    // System.out.println(product.getPrice());
    // redirectAttribute.addFlashAttribute("message", "The Product has been saved
    // successfully.");
    // return "The Product has been saved successfully.";
    // }

    // @PutMapping("/product/edit/{id}")
    // public String showEditForm(@PathVariable("id") Integer id,Model model){

    // if (productService.CheckAvalability(id) == null ){
    // throw new ProductNotFoundException("Student id Not Found - "+id);
    // }
    // Product prop= productService.getProductsById(id);
    // // return new ResponseEntity<Product>(prop, prop!=null ? HttpStatus.FOUND :
    // HttpStatus.NOT_FOUND);
    // model.addAttribute("Listproduct", prop);
    // return "products";
    // }

}
