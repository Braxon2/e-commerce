package com.dusan.ecommerce.dto;

import com.dusan.ecommerce.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record ProductDTO(
        Long id,
        String name,
        BigDecimal price,
        String shortDescription,
        String fullDescription,
        List<String> images,
        Map<String, String> technicalSpecifications
) {
    public ProductDTO(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getShortDescription(),
                product.getDescription(),
                product.getImages(),
                product.getTechnicalSpecifications()
        );
    }
}
