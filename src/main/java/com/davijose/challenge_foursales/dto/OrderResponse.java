package com.davijose.challenge_foursales.dto;

import com.davijose.challenge_foursales.domain.order.Order;
import com.davijose.challenge_foursales.domain.order.Status;
import com.davijose.challenge_foursales.domain.product.Product;

import java.util.UUID;

public record OrderResponse(
        Long id,
        Status status,
        UUID userId,
        Float total
) {
    public OrderResponse(Order order) {
        this(
                order.getId(),
                order.getStatus(),
                order.getUser().getId(),
                order.getTotal()
        );
    }
}
