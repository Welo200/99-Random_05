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

    // Add a new product
    public Product addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    // Get all products
    public ArrayList<Product> getProducts() {
        return productRepository.findAll();
    }

    // Get a specific product by ID
    public Product getProductById(UUID productId) {
        return productRepository.getProductById(productId);
    }

    // Update a product's name and price
    public Product updateProduct(UUID productId, String newName, double newPrice) {
        return productRepository.updateProduct(productId, newName, newPrice);
    }

    // Apply a discount to a list of products
    public void applyDiscount(double discount, ArrayList<UUID> productIds) {
        productRepository.applyDiscount(discount, productIds);
    }

    // Delete a product by ID
    public void deleteProductById(UUID productId) {
        productRepository.deleteProductById(productId);
    }
}