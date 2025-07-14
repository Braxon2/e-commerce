package com.dusan.ecommerce.service;

import com.dusan.ecommerce.dto.ProductDTO;
import com.dusan.ecommerce.exceptions.NoSuchElementException;
import com.dusan.ecommerce.model.Product;
import com.dusan.ecommerce.repository.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable).map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO getProductById(Long id) {
        return productRepository.findById(id)
                .map(ProductDTO::new)
                .orElseThrow(() -> new NoSuchElementException("Product not found with id: " + id));
    }
}
