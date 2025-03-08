package com.example.service;

import com.example.model.User;
import com.example.model.Order;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class UserService extends MainService<User> {

    private final UserRepository userRepository;

    @Autowired  
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Add a new user to the system
    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    // Retrieve all users from the system
    public ArrayList<User> getUsers() {
        return userRepository.getUsers();
    }

    // Fetch a specific user by their unique ID
    public User getUserById(UUID userId) {
        return userRepository.getUserById(userId);
    }

    // Retrieve all orders for a specific user
    public List<Order> getOrdersByUserId(UUID userId) {
        return userRepository.getOrdersByUserId(userId);
    }

    // Add a new order to a user's list of orders
    public void addOrderToUser(UUID userId) {
        User user = userRepository.getUserById(userId);
        if (user != null) {
            // Create a new order and calculate the total price based on the user's cart
            // This logic can be expanded based on your application's requirements
            Order order = new Order(UUID.randomUUID(), userId, 0.0, new ArrayList<>());
            userRepository.addOrderToUser(userId, order);
        }
    }

    // Empty the cart of a specific user
    public void emptyCart(UUID userId) {
        User user = userRepository.getUserById(userId);
        if (user != null) {
            // Logic to empty the cart (e.g., clear the cart's products)
            // This can be expanded based on your application's requirements
            userRepository.addOrderToUser(userId, null); // Placeholder for actual logic
        }
    }

    // Remove a specific order from a user's list of orders
    public void removeOrderFromUser(UUID userId, UUID orderId) {
        userRepository.removeOrderFromUser(userId, orderId);
    }

    // Delete a specific user by their ID
    public void deleteUserById(UUID userId) {
        userRepository.deleteUserById(userId);
    }
}