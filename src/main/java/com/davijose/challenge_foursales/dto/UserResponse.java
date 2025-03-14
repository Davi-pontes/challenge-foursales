package com.davijose.challenge_foursales.dto;

import com.davijose.challenge_foursales.domain.user.RoleUser;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String email,
        RoleUser roleUser
) {
}
