package com.beatriz.geolocation_feasibility_api.dto;

import java.time.Instant;

public record ErrorResponseDTO(
        String code,
        String reason,
        String message,
        String status,
        Instant timestamp
) {
}