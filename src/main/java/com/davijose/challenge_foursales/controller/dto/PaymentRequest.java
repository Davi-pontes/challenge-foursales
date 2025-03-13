package com.davijose.challenge_foursales.controller.dto;

import java.math.BigDecimal;

public record PaymentRequest(
        String paymentMethod,
        BigDecimal amount
) {}
