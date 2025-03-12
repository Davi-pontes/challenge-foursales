package com.davijose.challenge_foursales.domain.order;

public enum Status {
    WAITING_PAYMENT,
    PAYMENT_APPROVED,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    REFUNDED
}
