package com.example.MiniProject1;

import com.example.model.Order;
import com.example.model.User;
import com.example.repository.UserRepository;

import com.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User testUser;
    private UUID userId;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        testUser = new User();
        testUser.setId(userId);
        testUser.setName("Test User");
    }

    // ------------------------ User Tests -------------------------

    @Test
    void testAddUserSuccessfully() {
        when(userRepository.addUser(testUser)).thenReturn(testUser);

        User result = userService.addUser(testUser);

        assertNotNull(result);
        assertEquals(testUser, result);
        verify(userRepository, times(1)).addUser(testUser);
    }

    @Test
    void testAddUserNull() {
        when(userRepository.addUser(null)).thenReturn(null);

        User result = userService.addUser(null);

        assertNull(result);
        verify(userRepository, times(1)).addUser(null);
    }

    @Test
    void testAddUserDuplicate() {
        when(userRepository.addUser(testUser)).thenThrow(new RuntimeException("User already exists"));

        Exception exception = assertThrows(RuntimeException.class, () -> userService.addUser(testUser));
        assertEquals("User already exists", exception.getMessage());

        verify(userRepository, times(1)).addUser(testUser);
    }

    @Test
    void testGetUsersNotEmpty() {
        ArrayList<User> users = new ArrayList<>(List.of(testUser));
        when(userRepository.getUsers()).thenReturn(users);

        ArrayList<User> result = userService.getUsers();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(userRepository, times(1)).getUsers();
    }

    @Test
    void testGetUsersEmpty() {
        when(userRepository.getUsers()).thenReturn(new ArrayList<>());

        ArrayList<User> result = userService.getUsers();

        assertTrue(result.isEmpty());
        verify(userRepository, times(1)).getUsers();
    }

    @Test
    void testGetUsersFailure() {
        when(userRepository.getUsers()).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> userService.getUsers());
        assertEquals("Database error", exception.getMessage());

        verify(userRepository, times(1)).getUsers();
    }

    @Test
    void testGetUserByIdFound() {
        when(userRepository.getUserById(userId)).thenReturn(testUser);

        User result = userService.getUserById(userId);

        assertNotNull(result);
        assertEquals(testUser, result);
        verify(userRepository, times(1)).getUserById(userId);
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userRepository.getUserById(userId)).thenReturn(null);

        User result = userService.getUserById(userId);

        assertNull(result);
        verify(userRepository, times(1)).getUserById(userId);
    }

    @Test
    void testGetUserByIdException() {
        when(userRepository.getUserById(userId)).thenThrow(new RuntimeException("User retrieval failed"));

        Exception exception = assertThrows(RuntimeException.class, () -> userService.getUserById(userId));
        assertEquals("User retrieval failed", exception.getMessage());

        verify(userRepository, times(1)).getUserById(userId);
    }

    // ------------------------ Order Tests -------------------------

    @Test
    void testGetOrdersByUserIdWithOrders() {
        List<Order> orders = List.of(new Order(UUID.randomUUID(), userId, 100.0, new ArrayList<>()));
        when(userRepository.getOrdersByUserId(userId)).thenReturn(orders);

        List<Order> result = userService.getOrdersByUserId(userId);

        assertFalse(result.isEmpty());
        verify(userRepository, times(1)).getOrdersByUserId(userId);
    }

    @Test
    void testGetOrdersByUserIdNoOrders() {
        when(userRepository.getOrdersByUserId(userId)).thenReturn(new ArrayList<>());

        List<Order> result = userService.getOrdersByUserId(userId);

        assertTrue(result.isEmpty());
        verify(userRepository, times(1)).getOrdersByUserId(userId);
    }

    @Test
    void testGetOrdersByUserIdFailure() {
        when(userRepository.getOrdersByUserId(userId)).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> userService.getOrdersByUserId(userId));
        assertEquals("Database error", exception.getMessage());

        verify(userRepository, times(1)).getOrdersByUserId(userId);
    }

    @Test
    void testAddOrderToUserSuccess() {
        when(userRepository.getUserById(userId)).thenReturn(testUser);

        userService.addOrderToUser(userId);

        verify(userRepository, times(1)).addOrderToUser(eq(userId), any(Order.class));
    }

    @Test
    void testAddOrderToUserNoUser() {
        when(userRepository.getUserById(userId)).thenReturn(null);

        userService.addOrderToUser(userId);

        verify(userRepository, never()).addOrderToUser(any(), any());
    }

    @Test
    void testAddOrderToUserException() {
        when(userRepository.getUserById(userId)).thenThrow(new RuntimeException("Error retrieving user"));

        Exception exception = assertThrows(RuntimeException.class, () -> userService.addOrderToUser(userId));
        assertEquals("Error retrieving user", exception.getMessage());

        verify(userRepository, times(1)).getUserById(userId);
    }

    @Test
    void testEmptyCartUserExists() {
        when(userRepository.getUserById(userId)).thenReturn(testUser);
        doNothing().when(userRepository).addOrderToUser(userId, null);
        userService.emptyCart(userId);
        verify(userRepository, times(1)).getUserById(userId);
        verify(userRepository, times(1)).addOrderToUser(userId, null);
    }

    @Test
    void testEmptyCart_UserNotFound() {
        when(userRepository.getUserById(userId)).thenReturn(null);
        userService.emptyCart(userId);
        verify(userRepository, times(1)).getUserById(userId);
        verify(userRepository, never()).addOrderToUser(any(), any());
    }

    @Test
    void testEmptyCartNoExtraOperations() {
        // Arrange
        when(userRepository.getUserById(userId)).thenReturn(testUser);
        doNothing().when(userRepository).addOrderToUser(userId, null);

        // Act
        userService.emptyCart(userId);

        // Assert
        verify(userRepository, times(1)).getUserById(userId);
        verify(userRepository, times(1)).addOrderToUser(userId, null);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testRemoveOrderFromUserSuccess() {
        UUID orderId = UUID.randomUUID();

        userService.removeOrderFromUser(userId, orderId);

        verify(userRepository, times(1)).removeOrderFromUser(userId, orderId);
    }

    @Test
    void testRemoveOrderFromUserFailure() {
        UUID orderId = UUID.randomUUID();
        doThrow(new RuntimeException("Order not found")).when(userRepository).removeOrderFromUser(userId, orderId);

        Exception exception = assertThrows(RuntimeException.class, () -> userService.removeOrderFromUser(userId, orderId));
        assertEquals("Order not found", exception.getMessage());

        verify(userRepository, times(1)).removeOrderFromUser(userId, orderId);
    }

    @Test
    void testRemoveOrderFromUserNoExtraOperations() {
        // Act
        UUID orderId = UUID.randomUUID();
        userService.removeOrderFromUser(userId, orderId);

        // Assert
        verify(userRepository, times(1)).removeOrderFromUser(userId, orderId);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testDeleteUserByIdSuccess() {
        userService.deleteUserById(userId);

        verify(userRepository, times(1)).deleteUserById(userId);
    }

    @Test
    void testDeleteUserByIdFailure() {
        doThrow(new RuntimeException("User not found")).when(userRepository).deleteUserById(userId);

        Exception exception = assertThrows(RuntimeException.class, () -> userService.deleteUserById(userId));
        assertEquals("User not found", exception.getMessage());

        verify(userRepository, times(1)).deleteUserById(userId);
    }

    @Test
    void testDeleteUserByIdNoExtraOperations() {
        // Act
        userService.deleteUserById(userId);

        // Assert
        verify(userRepository, times(1)).deleteUserById(userId);
        verifyNoMoreInteractions(userRepository);
    }

}
