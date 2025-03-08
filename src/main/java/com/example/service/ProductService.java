package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class ProductService extends MainService<Product> {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

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