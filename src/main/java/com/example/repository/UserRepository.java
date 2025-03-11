package com.example.repository;

import com.example.model.Order;
import com.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository extends MainRepository<User> {

    public UserRepository() {
        super();
    }

    @Override
    protected String getDataPath() {
        return "src/main/java/com/example/data/users.json";
    }

    @Override
    protected Class<User[]> getArrayType() {
        return User[].class;
    }

    // Retrieve all users from the JSON file
    public ArrayList<User> getUsers() {
        return findAll();
    }

    // Fetch a specific user by their unique ID
    public User getUserById(UUID userId) {
        ArrayList<User> users = getUsers();
        return users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    // Add a new user to the JSON file
    public User addUser(User user) {
        ArrayList<User> users = getUsers();
        users.add(user);
        saveAll(users);
        return user;
    }

    // Retrieve all orders for a given user ID
    public List<Order> getOrdersByUserId(UUID userId) {
        User user = getUserById(userId);
        return user != null ? user.getOrders() : new ArrayList<>();
    }

    // Add an order to a user
    public void addOrderToUser(UUID userId, Order order) {
        ArrayList<User> users = getUsers();
        users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .ifPresent(user -> {
                    user.addOrder(order);
                    saveAll(users);
                });
    }

    // Remove an order from a user
    public void removeOrderFromUser(UUID userId, UUID orderId) {
        ArrayList<User> users = getUsers();
        users.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .ifPresent(user -> {
                    user.getOrders().removeIf(order -> order.getId().equals(orderId));
                    saveAll(users);
                });
    }

    // Delete a user by their ID
    public void deleteUserById(UUID userId) {
        ArrayList<User> users = getUsers();
        users.removeIf(user -> user.getId().equals(userId));
        saveAll(users);
    }
}