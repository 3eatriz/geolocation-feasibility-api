package com.beatriz.geolocation_feasibility_api.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Geolocation Feasibility API",
        version = "1.0",
        description = "API para verificar a viabilidade de geolocalização"
    )
)
public class SwaggerConfig {
}
