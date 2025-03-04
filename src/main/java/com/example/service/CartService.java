package com.example.service;

import org.springframework.stereotype.Service;
import com.example.model.Cart;
import com.example.model.Product;
import com.example.repository.CartRepository;

import java.util.*;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Add a new cart
    public Cart createCart(UUID userId) {
        Cart newCart = new Cart(UUID.randomUUID(), userId, new ArrayList<>());
        return cartRepository.addCart(newCart);
    }

    // Get all carts
    public List<Cart> getAllCarts() {
        return cartRepository.getCarts();
    }

    // Get a specific cart by ID
    public Cart getCartById(UUID cartId) {
        return cartRepository.getCartById(cartId);
    }

    // Get a cart by user ID
    public Cart getCartByUserId(UUID userId) {
        return cartRepository.getCartByUserId(userId);
    }

    // Add a product to a cart
    public void addProductToCart(UUID cartId, Product product) {
        cartRepository.addProductToCart(cartId, product);
    }

    // Remove a product from a cart
    public void removeProductFromCart(UUID cartId, Product product) {
        cartRepository.deleteProductFromCart(cartId, product);
    }

    // Delete a cart by ID
    public void deleteCart(UUID cartId) {
        cartRepository.deleteCartById(cartId);
    }
}
