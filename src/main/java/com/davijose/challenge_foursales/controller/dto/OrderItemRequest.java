package com.davijose.challenge_foursales.controller.dto;

import java.util.UUID;

public record OrderItemRequest(
        UUID productId
) {
}