package com.example.service;

import com.example.model.Order;
<<<<<<< HEAD
import com.example.model.Product;
import com.example.repository.OrderRepository;
=======
import com.example.repository.OrderRepository;
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
public class OrderService extends MainService<Order> {

    private final OrderRepository orderRepository;

<<<<<<< HEAD
=======
    @Autowired
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

<<<<<<< HEAD
    public void addOrder(Order order) {
       this.orderRepository.save(order);
    }

    public ArrayList<Order> getOrders() {
        return this.orderRepository.findAll();
    }

    public Order getOrderById(UUID orderId) {
        return this.orderRepository.getOrderById(orderId);
    }

    public void deleteOrderById(UUID orderId) throws IllegalArgumentException {
        Order order = this.orderRepository.getOrderById(orderId);
        if (order == null) {
            throw new IllegalArgumentException();
        }
        this.orderRepository.deleteOrderById(orderId);
    }


}
=======
    // Add a new order to the system
    public void addOrder(Order order) {
        orderRepository.addOrder(order);
    }

    // Retrieve all orders from the system
    public ArrayList<Order> getOrders() {
        return orderRepository.getOrders();
    }

    // Fetch a specific order by its unique ID
    public Order getOrderById(UUID orderId) {
        return orderRepository.getOrderById(orderId);
    }

    // Delete a specific order by its ID
    public void deleteOrderById(UUID orderId) {
        orderRepository.deleteOrderById(orderId);
    }
}
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
