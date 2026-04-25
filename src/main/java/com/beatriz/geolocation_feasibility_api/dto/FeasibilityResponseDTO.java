package com.beatriz.geolocation_feasibility_api.dto;

public record FeasibilityResponseDTO(
        Long id,
        String nome,
        Double latitude,
        Double longitude,
        Double radius
) {
}