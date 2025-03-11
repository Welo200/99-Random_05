package com.example.MiniProject1;

import com.example.model.Cart;
import com.example.model.Product;
import com.example.repository.CartRepository;
import com.example.service.CartService;
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
class CartServiceTests {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    private Cart cart;
    private UUID cartId;
    private UUID userId;
    private Product product;

    @BeforeEach
    void setUp() {
        cartId = UUID.randomUUID();
        userId = UUID.randomUUID();
        cart = new Cart(cartId, userId, new ArrayList<>());
        product = new Product(UUID.randomUUID(), "Test Product", 100.0);
    }

    /*** Test addCart() ***/
    @Test
    void testAddCartSuccess() {
        when(cartRepository.addCart(cart)).thenReturn(cart);
        Cart result = cartService.addCart(cart);
        assertNotNull(result);
        assertEquals(cart.getId(), result.getId());
        verify(cartRepository, times(1)).addCart(cart);
    }

    @Test
    void testAddCartNullCart() {
        when(cartRepository.addCart(null)).thenReturn(null);
        Cart result = cartService.addCart(null);
        assertNull(result);
        verify(cartRepository, times(1)).addCart(null);
    }

    @Test
    void testAddCartRepositoryFailure() {
        when(cartRepository.addCart(cart)).thenThrow(new RuntimeException("Database error"));
        assertThrows(RuntimeException.class, () -> cartService.addCart(cart));
        verify(cartRepository, times(1)).addCart(cart);
    }

    /*** Test getCarts() ***/
    @Test
    void testGetCartsSuccess() {
        ArrayList<Cart> carts = new ArrayList<>();
        carts.add(cart);
        when(cartRepository.getCarts()).thenReturn(carts);
        ArrayList<Cart> result = cartService.getCarts();
        assertEquals(1, result.size());
        verify(cartRepository, times(1)).getCarts();
    }

    @Test
    void testGetCartsEmptyList() {
        when(cartRepository.getCarts()).thenReturn(new ArrayList<>());
        ArrayList<Cart> result = cartService.getCarts();
        assertTrue(result.isEmpty());
        verify(cartRepository, times(1)).getCarts();
    }

    @Test
    void testGetCartsRepositoryFailure() {
        when(cartRepository.getCarts()).thenThrow(new RuntimeException("Database error"));
        assertThrows(RuntimeException.class, () -> cartService.getCarts());
        verify(cartRepository, times(1)).getCarts();
    }

    /*** Test getCartById() ***/
    @Test
    void testGetCartByIdSuccess() {
        when(cartRepository.getCartById(cartId)).thenReturn(cart);
        Cart result = cartService.getCartById(cartId);
        assertNotNull(result);
        assertEquals(cartId, result.getId());
        verify(cartRepository, times(1)).getCartById(cartId);
    }

    @Test
    void testGetCartByIdNotFound() {
        when(cartRepository.getCartById(cartId)).thenReturn(null);
        Cart result = cartService.getCartById(cartId);
        assertNull(result);
        verify(cartRepository, times(1)).getCartById(cartId);
    }

    @Test
    void testGetCartByIdRepositoryFailure() {
        when(cartRepository.getCartById(cartId)).thenThrow(new RuntimeException("Database error"));
        assertThrows(RuntimeException.class, () -> cartService.getCartById(cartId));
        verify(cartRepository, times(1)).getCartById(cartId);
    }

    /*** Test getCartByUserId() ***/
    @Test
    void testGetCartByUserIdSuccess() {
        when(cartRepository.getCartByUserId(userId)).thenReturn(cart);
        Cart result = cartService.getCartByUserId(userId);
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        verify(cartRepository, times(1)).getCartByUserId(userId);
    }

    @Test
    void testGetCartByUserIdNotFound() {
        when(cartRepository.getCartByUserId(userId)).thenReturn(null);
        Cart result = cartService.getCartByUserId(userId);
        assertNull(result);
        verify(cartRepository, times(1)).getCartByUserId(userId);
    }

    @Test
    void testGetCartByUserIdRepositoryFailure() {
        when(cartRepository.getCartByUserId(userId)).thenThrow(new RuntimeException("Database error"));
        assertThrows(RuntimeException.class, () -> cartService.getCartByUserId(userId));
        verify(cartRepository, times(1)).getCartByUserId(userId);
    }

    /*** Test addProductToCart() ***/
    @Test
    void testAddProductToCartSuccess() {
        cartService.addProductToCart(cartId, product);
        verify(cartRepository, times(1)).addProductToCart(cartId, product);
    }

    @Test
    void testAddProductToCartNullProduct() {
        cartService.addProductToCart(cartId, null);
        verify(cartRepository, times(1)).addProductToCart(cartId, null);
    }

    @Test
    void testAddProductToCartRepositoryFailure() {
        doThrow(new RuntimeException("Database error")).when(cartRepository).addProductToCart(cartId, product);
        assertThrows(RuntimeException.class, () -> cartService.addProductToCart(cartId, product));
        verify(cartRepository, times(1)).addProductToCart(cartId, product);
    }

    /*** Test deleteProductFromCart() ***/
    @Test
    void testDeleteProductFromCartSuccess() {
        cartService.deleteProductFromCart(cartId, product);
        verify(cartRepository, times(1)).deleteProductFromCart(cartId, product);
    }

    @Test
    void testDeleteProductFromCartNullProduct() {
        cartService.deleteProductFromCart(cartId, null);
        verify(cartRepository, times(1)).deleteProductFromCart(cartId, null);
    }

    @Test
    void testDeleteProductFromCartRepositoryFailure() {
        doThrow(new RuntimeException("Database error")).when(cartRepository).deleteProductFromCart(cartId, product);
        assertThrows(RuntimeException.class, () -> cartService.deleteProductFromCart(cartId, product));
        verify(cartRepository, times(1)).deleteProductFromCart(cartId, product);
    }

    /*** Test deleteCartById() ***/
    @Test
    void testDeleteCartByIdSuccess() {
        cartService.deleteCartById(cartId);
        verify(cartRepository, times(1)).deleteCartById(cartId);
    }

    @Test
    void testDeleteCartByIdNullCartId() {
        cartService.deleteCartById(null);
        verify(cartRepository, times(1)).deleteCartById(null);
    }

    @Test
    void testDeleteCartByIdRepositoryFailure() {
        doThrow(new RuntimeException("Database error")).when(cartRepository).deleteCartById(cartId);
        assertThrows(RuntimeException.class, () -> cartService.deleteCartById(cartId));
        verify(cartRepository, times(1)).deleteCartById(cartId);
    }
}
