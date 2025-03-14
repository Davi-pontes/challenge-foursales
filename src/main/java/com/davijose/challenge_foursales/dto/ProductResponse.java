package com.davijose.challenge_foursales.dto;

import com.davijose.challenge_foursales.domain.product.Product;

import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String description,
        Float price,
        Integer stock,
        String category
) {
    public ProductResponse(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory()
        );
    }
}
