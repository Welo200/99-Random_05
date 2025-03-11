package com.example.MiniProject1;


import com.example.model.Order;
import com.example.repository.OrderRepository;
import com.example.service.OrderService;
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
class OrderServiceTests {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;
    private UUID orderId;

    @BeforeEach
    void setUp() {
        orderId = UUID.randomUUID();
        order = new Order(orderId, UUID.randomUUID(), 200.0, new ArrayList<>());
    }

    /*** Test addOrder() ***/
    @Test
    void testAddOrderSuccess() {
        orderService.addOrder(order);
        verify(orderRepository, times(1)).addOrder(order);
    }

    @Test
    void testAddOrderNullOrder() {
        orderService.addOrder(null);
        verify(orderRepository, times(1)).addOrder(null);
    }

    @Test
    void testAddOrderRepositoryFailure() {
        doThrow(new RuntimeException("Database error")).when(orderRepository).addOrder(order);
        assertThrows(RuntimeException.class, () -> orderService.addOrder(order));
        verify(orderRepository, times(1)).addOrder(order);
    }

    /*** Test getOrders() ***/
    @Test
    void testGetOrdersSuccess() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderRepository.getOrders()).thenReturn(orders);
        ArrayList<Order> result = orderService.getOrders();
        assertEquals(1, result.size());
        verify(orderRepository, times(1)).getOrders();
    }

    @Test
    void testGetOrdersEmptyList() {
        when(orderRepository.getOrders()).thenReturn(new ArrayList<>());
        ArrayList<Order> result = orderService.getOrders();
        assertTrue(result.isEmpty());
        verify(orderRepository, times(1)).getOrders();
    }

    @Test
    void testGetOrdersRepositoryFailure() {
        when(orderRepository.getOrders()).thenThrow(new RuntimeException("Database error"));
        assertThrows(RuntimeException.class, () -> orderService.getOrders());
        verify(orderRepository, times(1)).getOrders();
    }

    /*** Test getOrderById() ***/
    @Test
    void testGetOrderByIdSuccess() {
        when(orderRepository.getOrderById(orderId)).thenReturn(order);
        Order result = orderService.getOrderById(orderId);
        assertNotNull(result);
        assertEquals(orderId, result.getId());
        verify(orderRepository, times(1)).getOrderById(orderId);
    }

    @Test
    void testGetOrderByIdNotFound() {
        when(orderRepository.getOrderById(orderId)).thenReturn(null);
        Order result = orderService.getOrderById(orderId);
        assertNull(result);
        verify(orderRepository, times(1)).getOrderById(orderId);
    }

    @Test
    void testGetOrderByIdRepositoryFailure() {
        when(orderRepository.getOrderById(orderId)).thenThrow(new RuntimeException("Database error"));
        assertThrows(RuntimeException.class, () -> orderService.getOrderById(orderId));
        verify(orderRepository, times(1)).getOrderById(orderId);
    }

    /*** Test deleteOrderById() ***/
    @Test
    void testDeleteOrderByIdSuccess() {
        orderService.deleteOrderById(orderId);
        verify(orderRepository, times(1)).deleteOrderById(orderId);
    }

    @Test
    void testDeleteOrderByIdNullOrderId() {
        orderService.deleteOrderById(null);
        verify(orderRepository, times(1)).deleteOrderById(null);
    }

    @Test
    void testDeleteOrderByIdRepositoryFailure() {
        doThrow(new RuntimeException("Database error")).when(orderRepository).deleteOrderById(orderId);
        assertThrows(RuntimeException.class, () -> orderService.deleteOrderById(orderId));
        verify(orderRepository, times(1)).deleteOrderById(orderId);
    }
}
