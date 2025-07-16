package com.dusan.ecommerce.model;

import com.dusan.ecommerce.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("John", "Doe", "john@example.com", "password123", Role.USER);
    }

    @Test
    void testDefaultConstructor() {
        User emptyUser = new User();
        assertNull(emptyUser.getFirstName());
        assertNull(emptyUser.getLastName());
        assertNull(emptyUser.getEmail());
        assertNull(emptyUser.getPassword());
        assertNull(emptyUser.getRole());
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    void testSettersAndGetters() {
        user.setId(1L);
        user.setFirstName("Jane");
        user.setLastName("Smith");
        user.setEmail("jane@example.com");
        user.setPassword("newPassword");
        user.setRole(Role.ADMIN);

        assertEquals(1L, user.getId());
        assertEquals("Jane", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("jane@example.com", user.getEmail());
        assertEquals("newPassword", user.getPassword());
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    void testCartRelationship() {
        Cart cart = new Cart();
        user.setCart(cart);

        assertEquals(cart, user.getCart());
    }

    @Test
    void testUserDetailsImplementation() {
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(Role.USER));
        assertEquals("john@example.com", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
    }

    @Test
    void testEquals() {
        User user1 = new User();
        User user2 = new User();

        assertEquals(user1, user2);

        user1.setId(1L);
        user2.setId(1L);
        assertEquals(user1, user2);

        user2.setId(2L);
        assertNotEquals(user1, user2);
    }

    @Test
    void testHashCode() {
        User user1 = new User();
        User user2 = new User();

        assertEquals(user1.hashCode(), user2.hashCode());

        user1.setId(1L);
        user2.setId(1L);
        assertEquals(user1.hashCode(), user2.hashCode());
    }


}