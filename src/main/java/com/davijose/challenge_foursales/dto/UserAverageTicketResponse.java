package com.davijose.challenge_foursales.dto;

import java.util.UUID;

public record UserAverageTicketResponse(
        UUID userId,
        String userName,
        Double averageTicket
) {
}
