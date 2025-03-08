package com.example.controller;

import com.example.model.Cart;
import com.example.model.Product;
import com.example.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;
//Update
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Add a new cart
    @PostMapping("/")
    public Cart addCart(@RequestBody Cart cart) {
        return cartService.addCart(cart);
    }

    // Get all carts
    @GetMapping("/")
    public ArrayList<Cart> getCarts() {
        return cartService.getCarts();
    }

    // Get a specific cart by ID
    @GetMapping("/{cartId}")
    public Cart getCartById(@PathVariable UUID cartId) {
        return cartService.getCartById(cartId);
    }

    // Add a product to a specific cart
    @PutMapping("/addProduct/{cartId}")
    public String addProductToCart(@PathVariable UUID cartId, @RequestBody Product product) {
        cartService.addProductToCart(cartId, product);
        return "Product added to cart successfully with cart ID: " + cartId;
    }

    // Delete a cart by ID
    @DeleteMapping("/delete/{cartId}")
    public String deleteCartById(@PathVariable UUID cartId) {
        cartService.deleteCartById(cartId);
        return "Cart deleted successfully with ID: " + cartId;
    }
}