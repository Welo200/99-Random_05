package com.example.MiniProject1;



import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private UUID productId;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        product = new Product(productId, "Test Product", 100.0);
    }

    // 1. addProduct() Tests
    @Test
    void testAddProductSuccess() {
        when(productRepository.addProduct(product)).thenReturn(product);
        Product addedProduct = productService.addProduct(product);
        assertNotNull(addedProduct);
        assertEquals("Test Product", addedProduct.getName());
        verify(productRepository, times(1)).addProduct(product);
    }

    @Test
    void testAddProductNullProduct() {
        assertThrows(IllegalArgumentException.class, () -> productService.addProduct(null));
    }

    @Test
    void testAddProductRepositoryFailure() {
        when(productRepository.addProduct(product)).thenThrow(new RuntimeException("DB Error"));
        assertThrows(RuntimeException.class, () -> productService.addProduct(product));
    }

    // 2. getProducts() Tests
    @Test
    void testGetProductsSuccess() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        when(productRepository.getProducts()).thenReturn(products);

        ArrayList<Product> result = productService.getProducts();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void testGetProductsEmptyList() {
        when(productRepository.getProducts()).thenReturn(new ArrayList<>());
        assertTrue(productService.getProducts().isEmpty());
    }

    @Test
    void testGetProductsRepositoryFailure() {
        when(productRepository.getProducts()).thenThrow(new RuntimeException("DB Error"));
        assertThrows(RuntimeException.class, () -> productService.getProducts());
    }

    // 3. getProductById() Tests
    @Test
    void testGetProductByIdSuccess() {
        when(productRepository.getProductById(productId)).thenReturn(product);
        Product foundProduct = productService.getProductById(productId);
        assertNotNull(foundProduct);
        assertEquals(productId, foundProduct.getId());
    }

    @Test
    void testGetProductByIdNotFound() {
        when(productRepository.getProductById(productId)).thenReturn(null);
        assertNull(productService.getProductById(productId));
    }

    @Test
    void testGetProductByIdRepositoryFailure() {
        when(productRepository.getProductById(productId)).thenThrow(new RuntimeException("DB Error"));
        assertThrows(RuntimeException.class, () -> productService.getProductById(productId));
    }

    // 4. updateProduct() Tests
    @Test
    void testUpdateProductSuccess() {
        when(productRepository.getProductById(productId)).thenReturn(product);

        Product updatedProduct = productService.updateProduct(productId, "Updated Name", 200.0);
        assertNotNull(updatedProduct);
        assertEquals("Updated Name", updatedProduct.getName());
        assertEquals(200.0, updatedProduct.getPrice());
    }

    @Test
    void testUpdateProductProductNotFound() {
        when(productRepository.getProductById(productId)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> productService.updateProduct(productId, "New Name", 200.0));
    }

    @Test
    void testUpdateProductInvalidPrice() {
        lenient().when(productRepository.getProductById(productId)).thenReturn(product);
        assertThrows(IllegalArgumentException.class, () -> productService.updateProduct(productId, "New Name", -50.0));
    }


    // 5. applyDiscount() Tests
    @Test
    void testApplyDiscountSuccess() {
        ArrayList<UUID> productIds = new ArrayList<>();
        productIds.add(productId);
        doNothing().when(productRepository).applyDiscount(10.0, productIds);

        productService.applyDiscount(10.0, productIds);
        verify(productRepository, times(1)).applyDiscount(10.0, productIds);
    }

    @Test
    void testApplyDiscountEmptyList() {
        ArrayList<UUID> productIds = new ArrayList<>();

        // Mock repository method to ensure no action is taken when list is empty
        doNothing().when(productRepository).applyDiscount(anyDouble(), eq(productIds));

        // Call the method
        productService.applyDiscount(10.0, productIds);

        // Verify that applyDiscount was still called, even if list is empty
        verify(productRepository, times(1)).applyDiscount(10.0, productIds);
    }



    @Test
    void testApplyDiscountNegativeDiscount() {
        ArrayList<UUID> productIds = new ArrayList<>();
        productIds.add(productId);
        assertThrows(IllegalArgumentException.class, () -> productService.applyDiscount(-5.0, productIds));
    }

    // 6. deleteProductById() Tests
    @Test
    void testDeleteProductByIdSuccess() {
        doNothing().when(productRepository).deleteProductById(productId);
        productService.deleteProductById(productId);
        verify(productRepository, times(1)).deleteProductById(productId);
    }

    @Test
    void testDeleteProductByIdNonExistentId() {
        doThrow(new IllegalArgumentException("Product not found")).when(productRepository).deleteProductById(productId);
        assertThrows(IllegalArgumentException.class, () -> productService.deleteProductById(productId));
    }

    @Test
    void testDeleteProductByIdRepositoryFailure() {
        doThrow(new RuntimeException("DB Error")).when(productRepository).deleteProductById(productId);
        assertThrows(RuntimeException.class, () -> productService.deleteProductById(productId));
    }
}
