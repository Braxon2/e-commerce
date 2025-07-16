package com.dusan.ecommerce.model;

import com.dusan.ecommerce.enums.Role;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ModelIntegrationTest {
    @Test
    void testUserCartRelationship() {
        User user = new User("John", "Doe", "john@example.com", "password", Role.USER);
        Cart cart = new Cart();

        user.setCart(cart);
        cart.setUser(user);

        assertEquals(cart, user.getCart());
        assertEquals(user, cart.getUser());
    }

    @Test
    void testCartItemRelationships() {
        User user = new User("John", "Doe", "john@example.com", "password", Role.USER);
        Cart cart = new Cart();
        Product product = new Product("Test Product", new BigDecimal("100.00"),
                "Short desc", "Long desc", null);

        user.setCart(cart);
        cart.setUser(user);

        CartItem item = new CartItem(cart, product, 2);
        cart.addItem(item);

        assertEquals(1, cart.getItems().size());
        assertEquals(cart, item.getCart());
        assertEquals(product, item.getProduct());
        assertEquals(2, item.getQuantity());
    }

    @Test
    void testCompleteEcommerceFlow() {
        User user = new User("Jane", "Smith", "jane@example.com", "password", Role.USER);

        Cart cart = new Cart();
        user.setCart(cart);
        cart.setUser(user);

        Product laptop = new Product("Laptop", new BigDecimal("1000.00"),
                "Gaming laptop", "High-performance laptop", null);
        Product mouse = new Product("Mouse", new BigDecimal("25.00"),
                "Wireless mouse", "Ergonomic wireless mouse", null);

        CartItem laptopItem = new CartItem(cart, laptop, 1);
        CartItem mouseItem = new CartItem(cart, mouse, 2);

        cart.addItem(laptopItem);
        cart.addItem(mouseItem);

        assertEquals(2, cart.getItems().size());
        assertEquals(user, cart.getUser());
        assertEquals(cart, user.getCart());

        cart.removeItem(mouseItem);
        assertEquals(1, cart.getItems().size());
        assertNull(mouseItem.getCart());
    }
}
