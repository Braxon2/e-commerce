package com.dusan.ecommerce.dto;

import java.math.BigDecimal;

public record CartItemDTO(
        Long id,
        Long productId,
        String productName,
        BigDecimal price,
        int quantity
) {}