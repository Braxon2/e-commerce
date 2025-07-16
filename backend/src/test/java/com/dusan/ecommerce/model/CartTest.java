package com.dusan.ecommerce.model;

import com.dusan.ecommerce.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private Cart cart;
    private User user;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        user = new User("John", "Doe", "john@example.com", "password", Role.USER);
    }

    @Test
    void testDefaultConstructor() {
        Cart newCart = new Cart();
        assertNotNull(newCart.getItems());
        assertTrue(newCart.getItems().isEmpty());
    }

    @Test
    void testParameterizedConstructor() {
        List<CartItem> items = new ArrayList<>();
        Cart cartWithItems = new Cart(items);
        assertNotNull(cartWithItems.getItems());
        assertEquals(items, cartWithItems.getItems());
    }

    @Test
    void testParameterizedConstructorWithNull() {
        Cart cartWithNullItems = new Cart(null);
        assertNotNull(cartWithNullItems.getItems());
        assertTrue(cartWithNullItems.getItems().isEmpty());
    }

    @Test
    void testSettersAndGetters() {
        cart.setId(1L);
        cart.setUser(user);

        assertEquals(1L, cart.getId());
        assertEquals(user, cart.getUser());
    }

    @Test
    void testAddItem() {
        Product product = new Product();
        product.setId(1L);
        CartItem item = new CartItem(cart, product, 2);

        cart.addItem(item);

        assertEquals(1, cart.getItems().size());
        assertTrue(cart.getItems().contains(item));
        assertEquals(cart, item.getCart());
    }

    @Test
    void testAddItemNull() {
        int initialSize = cart.getItems().size();
        cart.addItem(null);

        assertEquals(initialSize, cart.getItems().size());
    }

    @Test
    void testAddItemAlreadyExists() {
        Product product = new Product();
        product.setId(1L);
        CartItem item = new CartItem(cart, product, 2);

        cart.addItem(item);
        cart.addItem(item);

        assertEquals(1, cart.getItems().size());
    }

    @Test
    void testRemoveItem() {
        Product product = new Product();
        product.setId(1L);
        CartItem item = new CartItem(cart, product, 2);

        cart.addItem(item);
        assertEquals(1, cart.getItems().size());

        cart.removeItem(item);
        assertEquals(0, cart.getItems().size());
        assertNull(item.getCart());
    }

    @Test
    void testRemoveItemNotInCart() {
        Product product = new Product();
        product.setId(1L);
        CartItem item = new CartItem(cart, product, 2);

        int initialSize = cart.getItems().size();
        cart.removeItem(item);

        assertEquals(initialSize, cart.getItems().size());
    }

    @Test
    void testEquals() {
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        assertEquals(cart1, cart2);

        cart1.setId(1L);
        cart2.setId(1L);
        assertEquals(cart1, cart2);

        cart2.setId(2L);
        assertNotEquals(cart1, cart2);
    }

    @Test
    void testHashCode() {
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();

        assertEquals(cart1.hashCode(), cart2.hashCode());

        cart1.setId(1L);
        cart2.setId(1L);
        assertEquals(cart1.hashCode(), cart2.hashCode());
    }
}