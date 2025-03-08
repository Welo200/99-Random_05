package com.example.repository;

import com.example.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public class OrderRepository extends MainRepository<Order> {

    public OrderRepository() {
        super();
    }

    @Override
    protected String getDataPath() {
        return "src/main/java/com/example/data/orders.json";
    }

    @Override
    protected Class<Order[]> getArrayType() {
        return Order[].class;
    }

    // Add a new order to the JSON file
    public void addOrder(Order order) {
        ArrayList<Order> orders = findAll();
        orders.add(order);
        saveAll(orders);
    }

    // Retrieve all orders from the JSON file
    public ArrayList<Order> getOrders() {
        return findAll();
    }

    // Fetch a specific order by its unique ID
    public Order getOrderById(UUID orderId) {
        ArrayList<Order> orders = getOrders();
        return orders.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    // Delete a specific order by its ID
    public void deleteOrderById(UUID orderId) {
        ArrayList<Order> orders = getOrders();
        orders.removeIf(order -> order.getId().equals(orderId));
        saveAll(orders);
    }
}