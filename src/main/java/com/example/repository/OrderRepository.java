package com.example.repository;

import com.example.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
<<<<<<< HEAD
@SuppressWarnings("rawtypes")
public class OrderRepository extends MainRepository<Order> {
    @Override
    protected String getDataPath() {
        return "D:\\99-Random_05\\src\\main\\java\\com\\example\\data\\orders.json";
=======
public class OrderRepository extends MainRepository<Order> {

    public OrderRepository() {
        super();
    }

    @Override
    protected String getDataPath() {
        return "src/main/java/com/example/data/orders.json";
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
    }

    @Override
    protected Class<Order[]> getArrayType() {
        return Order[].class;
    }

<<<<<<< HEAD
    public OrderRepository() {
    }

    public void addOrder(Order order) {
        save(order);

    }

=======
    // Add a new order to the JSON file
    public void addOrder(Order order) {
        ArrayList<Order> orders = findAll();
        orders.add(order);
        saveAll(orders);
    }

    // Retrieve all orders from the JSON file
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
    public ArrayList<Order> getOrders() {
        return findAll();
    }

<<<<<<< HEAD
    public Order getOrderById(UUID orderId) {
        return findAll().stream().filter(order -> order.getId().equals(orderId)).findFirst().orElse(null);
    }

    public void deleteOrderById(UUID orderId) {
        Order order = getOrderById(orderId);
        if (order != null) {
            ArrayList<Order> orders = getOrders();
            orders.removeIf(order1-> order1.getId().equals(orderId));
            saveAll(orders);
        }

    }


=======
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
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
}