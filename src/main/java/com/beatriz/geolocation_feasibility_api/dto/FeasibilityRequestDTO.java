package com.beatriz.geolocation_feasibility_api.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record FeasibilityRequestDTO(
        @NotNull(message = "latitude is mandatory") 
        @DecimalMin(value = "-90.0") 
        @DecimalMax(value = "90.0") 
        Double latitude,
        
        @NotNull(message = "longitude is mandatory")
        @DecimalMin(value = "-180.0")
        @DecimalMax(value = "180.0")
        Double longitude,

        @NotNull(message = "radius is mandatory")
        @Min(value = 10)
        @Max(value = 1000)
        Integer radius,

        @Min(0)
        Integer page,

        @Min(1)
        @Max(20)
        Integer size

) {

}
