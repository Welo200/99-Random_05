package com.example.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class User {
    private UUID id;
    private String name;
    private List<Order> orders = new ArrayList<>();

    // Default constructor
    public User() {
        this.id = UUID.randomUUID(); // Automatically generate a unique ID
        this.orders = new ArrayList<>();
    }

    // Constructor with name
    public User(String name) {
        this.id = UUID.randomUUID(); // Automatically generate a unique ID
        this.name = name;
        this.orders = new ArrayList<>();
    }

    // Constructor with all fields
    public User(UUID id, String name, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.orders = orders != null ? orders : new ArrayList<>();
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    // Helper method to add an order to the user
    public void addOrder(Order order) {
        if (order != null) {
            this.orders.add(order);
        }
    }

    // Helper method to remove an order from the user
    public void removeOrder(Order order) {
        if (order != null) {
            this.orders.remove(order);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orders=" + orders +
                '}';
    }
}