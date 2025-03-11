package com.example.model;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Product {
    private UUID id;
    private String name;
    private double price;

    // Default constructor
    public Product() {
        this.id = UUID.randomUUID(); // Automatically generate a unique ID
    }

    // Constructor with name and price
    public Product(String name, double price) {
        this.id = UUID.randomUUID(); // Automatically generate a unique ID
        this.name = name;
        this.price = price;
    }

    // Constructor with all fields
    public Product(UUID id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Helper method to apply a discount to the product's price
    public void applyDiscount(double discountPercentage) {
        if (discountPercentage > 0 && discountPercentage <= 100) {
            this.price = this.price * (1 - (discountPercentage / 100));
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}