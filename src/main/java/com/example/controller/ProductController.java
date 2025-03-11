package com.example.controller;

import com.example.model.Product;
import com.example.service.ProductService;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

<<<<<<< HEAD
=======
    @Autowired
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

<<<<<<< HEAD
    @PostMapping("/")
    public Product addProduct(@RequestBody Product product) {
        return this.productService.addProduct(product);
    }

    @GetMapping("/")
    public ArrayList<Product> getProducts() {
        return this.productService.getProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable UUID productId) {
        return this.productService.getProductById(productId);
    }

    @PutMapping("/update/{productId}")
    public Product updateProduct(@PathVariable UUID productId, @RequestBody Map<String, Object>
            body) {
        return this.productService.updateProduct(productId, body.get("newName").toString(), (double)body.get("newPrice"));
    }

    @PutMapping("/applyDiscount")
    public String applyDiscount(@RequestParam double discount, @RequestBody ArrayList<UUID>
            productIds) {
        try{
            this.productService.applyDiscount(discount,productIds);
            return "Discount applied successfully";
        }
        catch(Exception e){
            return "fail to apply discount";
        }
    }

    @DeleteMapping("/delete/{productId}")
    public String deleteProductById(@PathVariable UUID productId) {
         this.productService.deleteProductById(productId);
         return "Product deleted successfully";
    }


}

=======
    // Add a new product
    @PostMapping("/")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    // Get all products
    @GetMapping("/")
    public ArrayList<Product> getProducts() {
        return productService.getProducts();
    }

    // Get a specific product by ID
    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable UUID productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("/update/{productId}")
    public Product updateProduct(@PathVariable UUID productId, @RequestBody Map<String, Object> body) {
        // Get newName and newPrice from the request body
        String newName = (String) body.get("newName");
        Double newPrice = (Double) body.get("newPrice");

        // Handle missing or null fields
        if (newName == null) {
            throw new IllegalArgumentException("newName cannot be null");
        }
        if (newPrice == null) {
            throw new IllegalArgumentException("newPrice cannot be null");
        }

        // Call the service to update the product
        return productService.updateProduct(productId, newName, newPrice);
    }

    // Apply a discount to a list of products
    @PutMapping("/applyDiscount")
    public String applyDiscount(@RequestParam double discount, @RequestBody ArrayList<UUID> productIds) {
        productService.applyDiscount(discount, productIds);
        return "Discount applied successfully";
    }

    // Delete a product by ID
    @DeleteMapping("/delete/{productId}")
    public String deleteProductById(@PathVariable UUID productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return "Product not found"; // Return the expected response
        }
        productService.deleteProductById(productId);
        return "Product deleted successfully";
    }
}
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
