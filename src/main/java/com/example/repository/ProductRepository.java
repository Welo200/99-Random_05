package com.example.repository;

import com.example.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class ProductRepository extends MainRepository<Product> {
    @Override
    protected String getDataPath() {
        return "D:\\99-Random_05\\src\\main\\java\\com\\example\\data\\products.json";
    }

    @Override
    protected Class<Product[]> getArrayType() {
        return Product[].class;
    }



    public ProductRepository() {

    }

    public Product addProduct(Product product) {
        save(product);
        return product;
    }

    public ArrayList<Product> getProducts() {
        return findAll();
    }

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

}