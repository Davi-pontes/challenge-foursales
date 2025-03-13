package com.davijose.challenge_foursales.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ProductRequestUpdate(
        @NotNull
        UUID id,
        String name,
        String description,
        @Positive
        Float price,
        @Positive
        Integer stock,
        String category
) {
}
