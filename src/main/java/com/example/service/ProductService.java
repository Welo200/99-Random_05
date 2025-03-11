package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
<<<<<<< HEAD
@SuppressWarnings("rawtypes")
=======
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
public class ProductService extends MainService<Product> {

    private final ProductRepository productRepository;

<<<<<<< HEAD
=======
    @Autowired
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

<<<<<<< HEAD
    public Product addProduct(Product product) {
        return this.productRepository.addProduct(product);
    }

    public ArrayList<Product> getProducts() {
        return this.productRepository.getProducts();
    }

    public Product getProductById(UUID productId) {
        return this.productRepository.getProductById(productId);
    }

    public Product updateProduct(UUID productId, String newName, double newPrice) {
        return this.productRepository.updateProduct(productId, newName, newPrice);
    }

    public void applyDiscount(double discount, ArrayList<UUID> productIds) {
        this.productRepository.applyDiscount(discount, productIds);
    }

    public void deleteProductById(UUID productId) {
         this.productRepository.deleteProductById(productId);
    }




}
=======
    // Add a new product to the system
    public Product addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    // Retrieve all products from the system
    public ArrayList<Product> getProducts() {
        return productRepository.getProducts();
    }

    // Fetch a specific product by its unique ID
    public Product getProductById(UUID productId) {
        return productRepository.getProductById(productId);
    }

    public Product updateProduct(UUID productId, String newName, double newPrice) {
        // Validate inputs
        if (newName == null || newName.isEmpty()) {
            throw new IllegalArgumentException("newName cannot be null or empty");
        }
        if (newPrice < 0) {
            throw new IllegalArgumentException("newPrice must be a positive value");
        }

        // Fetch the product from the repository
        Product product = productRepository.getProductById(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }

        // Update the product
        product.setName(newName);
        product.setPrice(newPrice);
        productRepository.save(product);

        return product;
    }

    // Apply a discount to a list of products
    public void applyDiscount(double discount, ArrayList<UUID> productIds) {
        productRepository.applyDiscount(discount, productIds);
    }

    // Delete a product by its ID
    public void deleteProductById(UUID productId) {
        productRepository.deleteProductById(productId);
    }
}
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
