package com.example.controller;

import com.example.model.User;
import com.example.model.Order;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Add a new user
    @PostMapping("/")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    // Get all users
    @GetMapping("/")
    public ArrayList<User> getUsers() {
        return userService.getUsers();
    }

    // Get a specific user by ID
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }

    // Get all orders for a specific user
    @GetMapping("/{userId}/orders")
    public List<Order> getOrdersByUserId(@PathVariable UUID userId) {
        return userService.getOrdersByUserId(userId);
    }

    // Checkout (create a new order for the user)
    @PostMapping("/{userId}/checkout")
    public String addOrderToUser(@PathVariable UUID userId) {
        userService.addOrderToUser(userId);
        return "Order created successfully for user with ID: " + userId;
    }

    // Remove a specific order from a user
    @PostMapping("/{userId}/removeOrder")
    public String removeOrderFromUser(@PathVariable UUID userId, @RequestParam UUID orderId) {
        userService.removeOrderFromUser(userId, orderId);
        return "Order removed successfully for user with ID: " + userId;
    }

    // Empty the cart of a specific user
    @DeleteMapping("/{userId}/emptyCart")
    public String emptyCart(@PathVariable UUID userId) {
        userService.emptyCart(userId);
        return "Cart emptied successfully for user with ID: " + userId;
    }

    // Add a product to the user's cart
    @PutMapping("/addProductToCart")
    public String addProductToCart(@RequestParam UUID userId, @RequestParam UUID productId) {
        // Logic to add a product to the cart (e.g., fetch product and add to cart)
        // This can be expanded based on your application's requirements
        return "Product added to cart successfully for user with ID: " + userId;
    }

    // Delete a product from the user's cart
    @PutMapping("/deleteProductFromCart")
    public String deleteProductFromCart(@RequestParam UUID userId, @RequestParam UUID productId) {
        // Logic to delete a product from the cart (e.g., fetch product and remove from cart)
        // This can be expanded based on your application's requirements
        return "Product removed from cart successfully for user with ID: " + userId;
    }

    // Delete a specific user by ID
    @DeleteMapping("/delete/{userId}")
    public String deleteUserById(@PathVariable UUID userId) {
        userService.deleteUserById(userId);
        return "User deleted successfully with ID: " + userId;
    }
}