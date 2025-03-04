package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class ProductService extends MainService<Product> {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return this.productRepository.addProduct(product);
    }

    public ArrayList<Product> getProducts() {
        return this.productRepository.getProducts();
    }

    public Product getProductById(UUID productId) {
        return this.productRepository.getProductById(productId);
    }

    public Product updateProduct(UUID productId, String newName, double newPrice) {
        return this.productRepository.updateProduct(productId, newName, newPrice);
    }

    public void applyDiscount(double discount, ArrayList<UUID> productIds) {
        this.productRepository.applyDiscount(discount, productIds);
    }

    public void deleteProductById(UUID productId) {
         this.productRepository.deleteProductById(productId);
    }




}
