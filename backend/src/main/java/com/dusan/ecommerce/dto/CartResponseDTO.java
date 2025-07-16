package com.dusan.ecommerce.dto;

import java.math.BigDecimal;
import java.util.List;

public record CartResponseDTO(
        List<CartItemView> items,
        BigDecimal total
) {
    public record CartItemView(Long id, String name, int quantity, BigDecimal price) {}
}
