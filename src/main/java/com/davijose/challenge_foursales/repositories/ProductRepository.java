package com.davijose.challenge_foursales.repositories;

import com.davijose.challenge_foursales.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
