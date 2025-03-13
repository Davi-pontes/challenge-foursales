package com.davijose.challenge_foursales.controller.dto;

import com.davijose.challenge_foursales.domain.order.Order;
import com.davijose.challenge_foursales.domain.order.Status;
import com.davijose.challenge_foursales.domain.product.Product;

import java.util.UUID;

public record OrderResponse(
        Long id,
        Status status,
        UUID userId
) {
    public OrderResponse(Order order) {
        this(
                order.getId(),
                order.getStatus(),
                order.getUser().getId()
        );
    }
}
