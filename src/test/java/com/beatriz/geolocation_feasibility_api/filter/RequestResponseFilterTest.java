package com.beatriz.geolocation_feasibility_api.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.beatriz.geolocation_feasibility_api.dto.FeasibilityRequestDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class FeasibilityRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidation_whenDataIsValid() {

        FeasibilityRequestDTO dto = new FeasibilityRequestDTO(
                -23.55,
                -46.63,
                100,
                0,
                10);

        Set<ConstraintViolation<FeasibilityRequestDTO>> violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldFailValidation_whenLatitudeIsInvalid() {

        FeasibilityRequestDTO dto = new FeasibilityRequestDTO(
                200.0, // inválido
                -46.63,
                100,
                0,
                10);

        Set<ConstraintViolation<FeasibilityRequestDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertTrue(
                violations.stream()
                        .anyMatch(v -> v.getPropertyPath().toString().equals("latitude")));
    }

    @Test
    void shouldFailValidation_whenRadiusIsBelowMinimum() {

        FeasibilityRequestDTO dto = new FeasibilityRequestDTO(
                -23.55,
                -46.63,
                5, // inválido (< 10)
                0,
                10);

        Set<ConstraintViolation<FeasibilityRequestDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertTrue(
                violations.stream()
                        .anyMatch(v -> v.getPropertyPath().toString().equals("radius")));
    }

    @Test
    void shouldFailValidation_whenLongitudeIsNull() {

        FeasibilityRequestDTO dto = new FeasibilityRequestDTO(
                -23.55,
                null, // inválido
                100,
                0,
                10);

        Set<ConstraintViolation<FeasibilityRequestDTO>> violations = validator.validate(dto);

        assertEquals(1, violations.size());
        assertTrue(
                violations.stream()
                        .anyMatch(v -> v.getPropertyPath().toString().equals("longitude")));
    }
}