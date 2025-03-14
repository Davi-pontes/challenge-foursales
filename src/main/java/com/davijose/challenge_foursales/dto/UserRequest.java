package com.davijose.challenge_foursales.dto;

import com.davijose.challenge_foursales.domain.user.RoleUser;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        String name,
        @NotBlank
        String email,
        String password,
        RoleUser roleUser
) {
}
