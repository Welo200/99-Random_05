package com.example.controller;

import com.example.model.Order;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Add a new order
    @PostMapping("/")
    public void addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
    }

    // Get all orders
    @GetMapping("/")
    public ArrayList<Order> getOrders() {
        return orderService.getOrders();
    }

    // Get a specific order by ID
    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable UUID orderId) {
        return orderService.getOrderById(orderId);
    }

    // Delete a specific order by ID
    @DeleteMapping("/delete/{orderId}")
    public String deleteOrderById(@PathVariable UUID orderId) {
        orderService.deleteOrderById(orderId);
        return "Order deleted successfully with ID: " + orderId;
    }
}