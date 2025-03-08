package com.example.service;

import com.example.model.Order;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class OrderService extends MainService<Order> {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Add a new order
    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    // Get all orders
    public ArrayList<Order> getOrders() {
        return orderRepository.findAll();
    }

    // Get a specific order by ID
    public Order getOrderById(UUID orderId) {
        return orderRepository.getOrderById(orderId);
    }

    // Delete an order by ID
    public void deleteOrderById(UUID orderId) {
        orderRepository.deleteOrderById(orderId);
    }
}