package com.example.model;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.example.model.Order;


@Component
public class User {
    private UUID id;
    private String name;
    private List<Order> orders;

    // No-argument constructor
    public User() {
        this.id = UUID.randomUUID();
        this.orders = new ArrayList<>();
    }

    // Constructor with all fields
    public User(UUID id, String name, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.orders = orders;
    }

    // Constructor with only name (generates ID and initializes empty orders list)
    public User(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.orders = new ArrayList<>();
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
}
