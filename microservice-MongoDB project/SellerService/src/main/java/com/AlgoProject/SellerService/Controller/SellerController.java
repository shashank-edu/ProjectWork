package com.AlgoProject.SellerService.Controller;


import com.AlgoProject.SellerService.Entities.Product;
import com.AlgoProject.SellerService.ExceptionalHandelling.ProductNotFoundException;
import com.AlgoProject.SellerService.Services.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import java.util.List;

// @RestController
@Controller
@ResponseBody
@Transactional
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/seller")
public class SellerController {

    private static final Logger logger = LogManager.getLogger(SellerController.class);

    @Autowired
    private ProductService productService;


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/product/name={name}")
    public Product findProductByName(@PathVariable String name, Model model, RedirectAttributes redirectAttribute) {
        // Returning product by Name
        Product product = productService.getProductByName(name);
        logger.info("Getting Product by Name",product);
        return product;
    }

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product)
    {
        logger.info("Adding product",product);
        return  productService.addproduct(product);

    }

    @PostMapping("/addProductList")
    public List<Product> addProductList(@RequestBody List<Product> products)
    {
        logger.info("Adding List of product",products);
        return  productService.addproductList(products);
    }

    @PutMapping("/updateProduct/id={id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product product) {
        Product prop = productService.updateProductbyId(product, id);
        // return new ResponseEntity<Product>(prop, prop != null ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND);
        logger.info("Updating product",product);
        return "Successfully Update";
    }
    @GetMapping("/deleteProduct/id={id}")
    public HttpStatus deleteProductById(@PathVariable int id, RedirectAttributes redirectAttribute) {
        try {
            productService.deleteProductById(id);
            redirectAttribute.addFlashAttribute("message", "Product ID- " + id + " Successfully deleted!");
        } catch (ProductNotFoundException e) {
            logger.error("Unable to fetch the Product",e);
            redirectAttribute.addFlashAttribute("message", e.getMessage());
        }
        logger.info("deleting Product by Id: ",id);
        return HttpStatus.ACCEPTED;
    }

    @GetMapping("/getbyseller/{id}")
    public List<Product> getProductBysellerId(@PathVariable(value = "id")int seller_Id)
    {
        logger.info("Product by Seller Id: ",seller_Id);
        return  productService.getProductBysellerId(seller_Id);
    }

}
