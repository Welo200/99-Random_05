package com.example.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class Cart {
    private UUID id;
    private UUID userId;
    private List<Product> products = new ArrayList<>();

    // Default constructor
    public Cart() {
        this.id = UUID.randomUUID(); // Automatically generate a unique ID
        this.products = new ArrayList<>();
    }

    // Constructor with userId
    public Cart(UUID userId) {
        this.id = UUID.randomUUID(); // Automatically generate a unique ID
        this.userId = userId;
        this.products = new ArrayList<>();
    }

    // Constructor with all fields
    public Cart(UUID id, UUID userId, List<Product> products) {
        this.id = id;
        this.userId = userId;
        this.products = products != null ? products : new ArrayList<>();
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // Helper method to add a product to the cart
    public void addProduct(Product product) {
        if (product != null) {
            this.products.add(product);
        }
    }

    // Helper method to remove a product from the cart
    public void removeProduct(Product product) {
        if (product != null) {
            this.products.remove(product);
        }
    }

    // Helper method to calculate the total price of the cart
    public double calculateTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", userId=" + userId +
                ", products=" + products +
                '}';
    }
}