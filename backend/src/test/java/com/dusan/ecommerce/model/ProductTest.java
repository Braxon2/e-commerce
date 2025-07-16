package com.dusan.ecommerce.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        Map<String, String> specs = new HashMap<>();
        specs.put("Color", "Red");
        specs.put("Weight", "1kg");

        product = new Product("Laptop", new BigDecimal("999.99"), "Gaming laptop",
                "High-performance gaming laptop", specs);
    }

    @Test
    void testDefaultConstructor() {
        Product emptyProduct = new Product();
        assertNull(emptyProduct.getName());
        assertNull(emptyProduct.getPrice());
        assertNull(emptyProduct.getShortDescription());
        assertNull(emptyProduct.getDescription());
        assertNull(emptyProduct.getTechnicalSpecifications());
    }

    @Test
    void testParameterizedConstructor() {
        assertEquals("Laptop", product.getName());
        assertEquals(new BigDecimal("999.99"), product.getPrice());
        assertEquals("Gaming laptop", product.getShortDescription());
        assertEquals("High-performance gaming laptop", product.getDescription());
        assertNotNull(product.getTechnicalSpecifications());
        assertEquals("Red", product.getTechnicalSpecifications().get("Color"));
    }

    @Test
    void testSettersAndGetters() {
        product.setId(1L);
        product.setName("Desktop");
        product.setPrice(new BigDecimal("1200.00"));
        product.setShortDescription("Gaming desktop");
        product.setDescription("High-end gaming desktop");

        Map<String, String> newSpecs = new HashMap<>();
        newSpecs.put("CPU", "Intel i7");
        product.setTechnicalSpecifications(newSpecs);

        List<String> images = Arrays.asList("image1.jpg", "image2.jpg");
        product.setImages(images);

        assertEquals(1L, product.getId());
        assertEquals("Desktop", product.getName());
        assertEquals(new BigDecimal("1200.00"), product.getPrice());
        assertEquals("Gaming desktop", product.getShortDescription());
        assertEquals("High-end gaming desktop", product.getDescription());
        assertEquals(newSpecs, product.getTechnicalSpecifications());
        assertEquals(images, product.getImages());
    }

    @Test
    void testTechnicalSpecifications() {
        Map<String, String> specs = new HashMap<>();
        specs.put("RAM", "16GB");
        specs.put("Storage", "1TB SSD");

        product.setTechnicalSpecifications(specs);

        assertEquals("16GB", product.getTechnicalSpecifications().get("RAM"));
        assertEquals("1TB SSD", product.getTechnicalSpecifications().get("Storage"));
    }

    @Test
    void testImages() {
        List<String> images = Arrays.asList("front.jpg", "back.jpg", "side.jpg");
        product.setImages(images);

        assertEquals(3, product.getImages().size());
        assertTrue(product.getImages().contains("front.jpg"));
        assertTrue(product.getImages().contains("back.jpg"));
        assertTrue(product.getImages().contains("side.jpg"));
    }

}