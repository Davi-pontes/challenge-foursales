package com.davijose.challenge_foursales.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(
        @NotBlank
        String name,
        String description,
        @NotNull
        @Positive
        Float price,
        @NotNull
        Integer stock,
        @NotBlank
        String category
        ){}