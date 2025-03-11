package com.example.repository;

import com.example.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
<<<<<<< HEAD
@SuppressWarnings("rawtypes")
public class ProductRepository extends MainRepository<Product> {
    @Override
    protected String getDataPath() {
        return "D:\\99-Random_05\\src\\main\\java\\com\\example\\data\\products.json";
=======
public class ProductRepository extends MainRepository<Product> {

    public ProductRepository() {
        super();
    }

    @Override
    protected String getDataPath() {
        return "src/main/java/com/example/data/products.json";
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
    }

    @Override
    protected Class<Product[]> getArrayType() {
        return Product[].class;
    }

<<<<<<< HEAD


    public ProductRepository() {

    }

    public Product addProduct(Product product) {
        save(product);
        return product;
    }

=======
    // Add a new product to the JSON file
    public Product addProduct(Product product) {
        ArrayList<Product> products = findAll();
        products.add(product);
        saveAll(products);
        return product;
    }

    // Retrieve all products from the JSON file
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
    public ArrayList<Product> getProducts() {
        return findAll();
    }

<<<<<<< HEAD
    public Product getProductById(UUID productId) {
        return findAll().stream().filter(product -> product.getId().equals(productId)).findFirst().orElse(null);
    }

    public Product updateProduct(UUID productId, String newName, double newPrice) {
        Product product = getProductById(productId);
        if (product != null) {
            ArrayList<Product> products = findAll();
            products.removeIf(prod-> prod.getId().equals(productId));
            product.setName(newName);
            product.setPrice(newPrice);
            products.add(product);
            saveAll(products);
            return product;
        }
        return null;

    }

    public void applyDiscount(double discount, ArrayList<UUID> productIds) {
        ArrayList<Product> products = findAll();
        for (UUID productId : productIds) {
            Product product = getProductById(productId);
            if (product != null) {
                double newPrice = product.getPrice() - (product.getPrice() * (discount/100.0));
                product.setPrice(newPrice);
                products.removeIf(prod-> prod.getId().equals(productId));
                products.add(product);
                saveAll(products);
            }
        }

    }

    public void deleteProductById(UUID productId) {
        Product product = getProductById(productId);
        if (product != null) {
            ArrayList<Product> products = findAll();
            products.removeIf(prod-> prod.getId().equals(productId));
            saveAll(products);
        }

    }

=======
    // Fetch a specific product by its unique ID
    public Product getProductById(UUID productId) {
        ArrayList<Product> products = getProducts();
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .orElse(null);
    }

    // Update a product with a new name and price
    public Product updateProduct(UUID productId, String newName, double newPrice) {
        ArrayList<Product> products = getProducts();
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                product.setName(newName);
                product.setPrice(newPrice);
                saveAll(products);
                return product;
            }
        }
        return null; // Product not found
    }

    // Apply a discount to a list of products
    public void applyDiscount(double discount, ArrayList<UUID> productIds) {
        ArrayList<Product> products = getProducts();
        for (Product product : products) {
            if (productIds.contains(product.getId())) {
                product.applyDiscount(discount);
            }
        }
        saveAll(products);
    }

    // Delete a product by its ID
    public void deleteProductById(UUID productId) {
        ArrayList<Product> products = getProducts();
        products.removeIf(product -> product.getId().equals(productId));
        saveAll(products);
    }
>>>>>>> 8e02dc5846d96165253fe0a1cdbd0768f6a37f0d
}