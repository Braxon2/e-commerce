package com.dusan.ecommerce.service;

import com.dusan.ecommerce.dto.CartItemDTO;
import com.dusan.ecommerce.dto.CartResponseDTO;
import com.dusan.ecommerce.exceptions.NoSuchElementException;
import com.dusan.ecommerce.model.Cart;
import com.dusan.ecommerce.model.CartItem;
import com.dusan.ecommerce.model.Product;
import com.dusan.ecommerce.model.User;
import com.dusan.ecommerce.repository.CartItemRepository;
import com.dusan.ecommerce.repository.CartRepository;
import com.dusan.ecommerce.repository.ProductRepository;
import com.dusan.ecommerce.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public void addToCart(CartItemDTO dto) {
        User user = getCurrentUser();
        Cart cart = user.getCart();

        System.out.println("Initial cart: " + cart); // Debug log

        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
            cart = cartRepository.save(cart);
            System.out.println("Created cart: " + cart); // Debug log
            System.out.println("Cart ID: " + (cart != null ? cart.getId() : "null")); // Debug log
        }

        // Additional null check
        if (cart == null) {
            throw new RuntimeException("Failed to create cart for user: " + user.getId());
        }

        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new NoSuchElementException("Product not found"));

        // Check if cart.getItems() is null
        List<CartItem> items = cart.getItems();
        System.out.println("Cart items list: " + items); // Debug log

        if (items == null) {
            throw new RuntimeException("Cart items list is null for cart: " + cart.getId());
        }

        CartItem item = items.stream()
                .filter(ci -> ci.getProduct().getId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (item == null) {
            item = new CartItem();
            item.setProduct(product);
            item.setQuantity(dto.quantity());
            cart.addItem(item);
        } else {
            item.setQuantity(item.getQuantity() + dto.quantity());
        }

        cartRepository.save(cart);
    }


    public CartResponseDTO getCart() {
        User user = getCurrentUser();
        Cart cart = user.getCart();

        // Automatically create an empty cart if none exists
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
            userRepository.save(user); // make sure you persist the change
        }

        BigDecimal total = cart.getItems().stream()
                .map(i -> i.getProduct().getPrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<CartResponseDTO.CartItemView> items = cart.getItems().stream()
                .map(i -> new CartResponseDTO.CartItemView(
                        i.getProduct().getId(),
                        i.getProduct().getName(),
                        i.getQuantity(),
                        i.getProduct().getPrice()
                ))
                .toList();

        return new CartResponseDTO(items, total);
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(!optionalUser.isPresent()){
            throw new NoSuchElementException("User not found");
        }

        return optionalUser.get();
    }

    public void updateItemQuantity(Long itemId, int quantity) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new NoSuchElementException("Item not found"));
        item.setQuantity(quantity);
        cartItemRepository.save(item);
    }

    public void removeItemFromCart(Long itemId) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new NoSuchElementException("Item not found"));
        cartItemRepository.delete(item);
    }
}
