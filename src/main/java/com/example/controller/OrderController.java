package com.example.controller;

import com.example.model.Order;
import com.example.service.OrderService;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

<<<<<<< HEAD
=======
    @Autowired
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

<<<<<<< HEAD
    @PostMapping("/")
    public void addOrder(@RequestBody Order order) {
       this.orderService.addOrder(order);
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable UUID orderId) {
        return this.orderService.getOrderById(orderId);
    }

    @GetMapping("/")
    public ArrayList<Order> getOrders() {
        return this.orderService.getOrders();
=======
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
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
    }

    @DeleteMapping("/delete/{orderId}")
    public String deleteOrderById(@PathVariable UUID orderId) {
<<<<<<< HEAD
        try{
            this.orderService.deleteOrderById(orderId);
            return "Order deleted successfully";
        }
        catch(Exception e){
            return "Order not found";
        }
    }


}
=======
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return "Order not found"; // Return the expected response
        }
        orderService.deleteOrderById(orderId);
        return "Order deleted successfully";
    }
}
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
