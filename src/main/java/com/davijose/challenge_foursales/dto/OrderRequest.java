package com.davijose.challenge_foursales.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record OrderRequest(
        @NotNull
        UUID userId,
        List<OrderItemRequest> orderItems
) {
}
