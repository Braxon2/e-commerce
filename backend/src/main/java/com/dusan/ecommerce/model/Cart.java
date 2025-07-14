package com.dusan.ecommerce.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Cart {
    @Id
    @GeneratedValue
    private Long id;



    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



    public Cart() {
    }

    public Cart( List<CartItem> items) {
        this.items = items;
    }

    public void addItem(CartItem item) {
        if (item != null && !items.contains(item)) {
            items.add(item);
            item.setCart(this);
        }
    }

    public void removeItem(CartItem item) {
        if (items.remove(item)) {
            item.setCart(null);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

