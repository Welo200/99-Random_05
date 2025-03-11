package com.example.service;

import com.example.model.Cart;
import com.example.model.Product;
import com.example.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class CartService extends MainService<Cart> {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Add a new cart to the system
    public Cart addCart(Cart cart) {
        return cartRepository.addCart(cart);
    }

    // Retrieve all carts from the system
    public ArrayList<Cart> getCarts() {
        return cartRepository.getCarts();
    }

    // Fetch a specific cart by its unique ID
    public Cart getCartById(UUID cartId) {
        return cartRepository.getCartById(cartId);
    }

    // Get the cart of a specific user by their ID
    public Cart getCartByUserId(UUID userId) {
        return cartRepository.getCartByUserId(userId);
    }

    // Add a product to a specific cart
    public void addProductToCart(UUID cartId, Product product) {
        cartRepository.addProductToCart(cartId, product);
    }

    // Delete a product from a specific cart
    public void deleteProductFromCart(UUID cartId, Product product) {
        cartRepository.deleteProductFromCart(cartId, product);
    }

    // Delete a cart by its ID
    public void deleteCartById(UUID cartId) {
        cartRepository.deleteCartById(cartId);
    }
}