package com.davijose.challenge_foursales.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryRepository, UUID> {
}
