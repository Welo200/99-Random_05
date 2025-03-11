package com.example.model;

<<<<<<< HEAD

=======
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class Order {
<<<<<<< HEAD

    private UUID id;
    private UUID userId;
    private double totalPrice;
    private List<Product> products=new ArrayList<>();

    public Order() {
        this.id = UUID.randomUUID();

    }


=======
    private UUID id;
    private UUID userId;
    private double totalPrice;
    private List<Product> products = new ArrayList<>();

    // Default constructor
    public Order() {
        this.id = UUID.randomUUID(); // Automatically generate a unique ID
        this.products = new ArrayList<>();
    }

    // Constructor with userId and products
    public Order(UUID userId, List<Product> products) {
        this.id = UUID.randomUUID(); // Automatically generate a unique ID
        this.userId = userId;
        this.products = products != null ? products : new ArrayList<>();
        this.totalPrice = calculateTotalPrice(); // Calculate the total price based on the products
    }

    // Constructor with all fields
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
    public Order(UUID id, UUID userId, double totalPrice, List<Product> products) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
<<<<<<< HEAD
        this.products = products;
    }

    public Order(UUID userId, double totalPrice, List<Product> products) {
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.products = products;
    }



=======
        this.products = products != null ? products : new ArrayList<>();
    }

    // Getters and Setters
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
<<<<<<< HEAD
}
=======

    // Helper method to calculate the total price of the order
    public double calculateTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    // Helper method to add a product to the order
    public void addProduct(Product product) {
        if (product != null) {
            this.products.add(product);
            this.totalPrice = calculateTotalPrice(); // Recalculate the total price
        }
    }

    // Helper method to remove a product from the order
    public void removeProduct(Product product) {
        if (product != null) {
            this.products.remove(product);
            this.totalPrice = calculateTotalPrice(); // Recalculate the total price
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", totalPrice=" + totalPrice +
                ", products=" + products +
                '}';
    }
}
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
