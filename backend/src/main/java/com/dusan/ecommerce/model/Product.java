package com.dusan.ecommerce.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Entity
public class Product {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private BigDecimal price;
    private String shortDescription;

    @Lob
    private String description;

    @ElementCollection
    private Map<String, String> technicalSpecifications;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url")
    private List<String> images;

    public Product() {
    }

    public Product(String name, BigDecimal price, String shortDescription, String description, Map<String, String> technicalSpecifications) {
        this.name = name;
        this.price = price;
        this.shortDescription = shortDescription;
        this.description = description;
        this.technicalSpecifications = technicalSpecifications;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getTechnicalSpecifications() {
        return technicalSpecifications;
    }

    public void setTechnicalSpecifications(Map<String, String> technicalSpecifications) {
        this.technicalSpecifications = technicalSpecifications;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}

