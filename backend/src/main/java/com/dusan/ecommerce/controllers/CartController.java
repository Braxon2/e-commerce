package com.dusan.ecommerce.controllers;

import com.dusan.ecommerce.dto.CartItemDTO;
import com.dusan.ecommerce.dto.CartResponseDTO;
import com.dusan.ecommerce.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addToCart(@RequestBody CartItemDTO dto) {
        cartService.addToCart(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<CartResponseDTO> getCart() {
        return ResponseEntity.ok(cartService.getCart());
    }

    @PutMapping("/item/{id}")
    public void updateCartItem(@PathVariable Long id, @RequestParam int quantity) {
        cartService.updateItemQuantity(id, quantity);
    }

    @DeleteMapping("/item/{id}")
    public void deleteCartItem(@PathVariable Long id) {
        cartService.removeItemFromCart(id);
    }


}
