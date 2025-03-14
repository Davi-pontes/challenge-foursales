package com.davijose.challenge_foursales.dto;

import java.util.UUID;

public record OrderItemRequest(
        UUID productId,
        Integer quantity
) {
}