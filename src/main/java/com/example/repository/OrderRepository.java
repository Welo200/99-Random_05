package com.example.repository;

import com.example.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class OrderRepository extends MainRepository<Order> {
    @Override
    protected String getDataPath() {
        return "D:\\99-Random_05\\src\\main\\java\\com\\example\\data\\orders.json";
    }

    @Override
    protected Class<Order[]> getArrayType() {
        return Order[].class;
    }

    public OrderRepository() {
    }

    public void addOrder(Order order) {
        save(order);

    }

    public ArrayList<Order> getOrders() {
        return findAll();
    }

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


}