package com.example.repository;

import com.example.model.Cart;
import com.example.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public class CartRepository extends MainRepository<Cart> {

    public CartRepository() {
        super();
    }

    @Override
    protected String getDataPath() {
        return "src/main/java/com/example/data/carts.json";
    }

    @Override
    protected Class<Cart[]> getArrayType() {
        return Cart[].class;
    }

    // Add a new cart to the JSON file
    public Cart addCart(Cart cart) {
        ArrayList<Cart> carts = findAll();
        carts.add(cart);
        saveAll(carts);
        return cart;
    }

    // Retrieve all carts from the JSON file
    public ArrayList<Cart> getCarts() {
        return findAll();
    }

    // Fetch a specific cart by its unique ID
    public Cart getCartById(UUID cartId) {
        ArrayList<Cart> carts = getCarts();
        return carts.stream()
                .filter(cart -> cart.getId().equals(cartId))
                .findFirst()
                .orElse(null);
    }

    // Get the cart of a specific user by their ID
    public Cart getCartByUserId(UUID userId) {
        ArrayList<Cart> carts = getCarts();
        return carts.stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    // Add a product to a specific cart
    public void addProductToCart(UUID cartId, Product product) {
        ArrayList<Cart> carts = getCarts();
        for (Cart cart : carts) {
            if (cart.getId().equals(cartId)) {
                cart.addProduct(product);
                saveAll(carts);
                break;
            }
        }
    }

    // Delete a product from a specific cart
    public void deleteProductFromCart(UUID cartId, Product product) {
        ArrayList<Cart> carts = getCarts();
        for (Cart cart : carts) {
            if (cart.getId().equals(cartId)) {
                cart.removeProduct(product);
                saveAll(carts);
                break;
            }
        }
    }

    // Delete a cart by its ID
    public void deleteCartById(UUID cartId) {
        ArrayList<Cart> carts = getCarts();
        carts.removeIf(cart -> cart.getId().equals(cartId));
        saveAll(carts);
    }
}