package com.dusan.ecommerce.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    private CartItem cartItem;
    private Cart cart;
    private Product product;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        product = new Product("Test Product", new BigDecimal("50.00"),
                "Test description", "Full description", null);
        cartItem = new CartItem(cart, product, 3);
    }

    @Test
    void testDefaultConstructor() {
        CartItem emptyItem = new CartItem();
        assertNull(emptyItem.getCart());
        assertNull(emptyItem.getProduct());
        assertEquals(0, emptyItem.getQuantity());
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals(cart, cartItem.getCart());
        assertEquals(product, cartItem.getProduct());
        assertEquals(3, cartItem.getQuantity());
    }

    @Test
    void testSettersAndGetters() {
        Cart newCart = new Cart();
        Product newProduct = new Product();

        cartItem.setId(1L);
        cartItem.setCart(newCart);
        cartItem.setProduct(newProduct);
        cartItem.setQuantity(5);

        assertEquals(1L, cartItem.getId());
        assertEquals(newCart, cartItem.getCart());
        assertEquals(newProduct, cartItem.getProduct());
        assertEquals(5, cartItem.getQuantity());
    }

    @Test
    void testQuantityUpdate() {
        cartItem.setQuantity(10);
        assertEquals(10, cartItem.getQuantity());

        cartItem.setQuantity(0);
        assertEquals(0, cartItem.getQuantity());
    }

    @Test
    void testRelationships() {
        Cart anotherCart = new Cart();
        cartItem.setCart(anotherCart);
        assertEquals(anotherCart, cartItem.getCart());

        Product anotherProduct = new Product();
        cartItem.setProduct(anotherProduct);
        assertEquals(anotherProduct, cartItem.getProduct());
    }
}