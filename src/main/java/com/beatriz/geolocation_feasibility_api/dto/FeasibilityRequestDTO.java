package com.beatriz.geolocation_feasibility_api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record FeasibilityRequestDTO(

        @NotNull(message = "latitude is mandatory")
        @DecimalMin(
                value = "-90.0",
                message = "latitude must be greater than or equal to -90"
        )
        @DecimalMax(
                value = "90.0",
                message = "latitude must be less than or equal to 90"
        )
        Double latitude,

        @NotNull(message = "longitude is mandatory")
        @DecimalMin(
                value = "-180.0",
                message = "longitude must be greater than or equal to -180"
        )
        @DecimalMax(
                value = "180.0",
                message = "longitude must be less than or equal to 180"
        )
        Double longitude,

        @NotNull(message = "radius is mandatory")
        @Min(
                value = 10,
                message = "radius must be greater than or equal to 10"
        )
        @Max(
                value = 1000,
                message = "radius must be less than or equal to 1000"
        )
        Integer radius,

        @Min(
                value = 0,
                message = "page must be greater than or equal to 0"
        )
        Integer page,

        @Min(
                value = 1,
                message = "size must be greater than or equal to 1"
        )
        @Max(
                value = 20,
                message = "size must be less than or equal to 20"
        )
        Integer size

) {
}