package com.davijose.challenge_foursales.repositories;

import com.davijose.challenge_foursales.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
