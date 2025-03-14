package com.davijose.challenge_foursales.controller.dto;

import java.util.UUID;

public record UserCountResponse(
        UUID userId,
        String userName,
        Long totalOrders
) {
}
