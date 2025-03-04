package com.example.service;

import org.springframework.stereotype.Service;
import com.example.model.User;
import com.example.model.Order;
import com.example.repository.UserRepository;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.getUsers();
    }

    // Get a user by ID
    public User getUserById(UUID userId) {
        return userRepository.getUserById(userId);
    }

    // Add a new user
    public User createUser(String name) {
        User newUser = new User(UUID.randomUUID(), name, new ArrayList<>());
        return userRepository.addUser(newUser);
    }

    // Get orders of a user
    public List<Order> getOrdersByUserId(UUID userId) {
        return userRepository.getOrdersByUserId(userId);
    }

    // Add an order to a user
    public void addOrderToUser(UUID userId, Order order) {
        userRepository.addOrderToUser(userId, order);
    }

    // Remove an order from a user
    public void removeOrderFromUser(UUID userId, UUID orderId) {
        userRepository.removeOrderFromUser(userId, orderId);
    }

    // Delete a user
    public void deleteUser(UUID userId) {
        userRepository.deleteUserById(userId);
    }
}
