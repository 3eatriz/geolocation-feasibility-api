package com.beatriz.geolocation_feasibility_api.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import jakarta.validation.ConstraintViolation;

import static org.junit.jupiter.api.Assertions.*;

class FeasibilityRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldFailWhenLatitudeIsInvalid() {

        FeasibilityRequestDTO dto =
                new FeasibilityRequestDTO(200.0, -46.63, 100, 0, 10);

        Set<ConstraintViolation<FeasibilityRequestDTO>> violations =
                validator.validate(dto);

        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldFailWhenRadiusIsBelowMinimum() {

        FeasibilityRequestDTO dto =
                new FeasibilityRequestDTO(-23.55, -46.63, 5, 0, 10);

        Set<ConstraintViolation<FeasibilityRequestDTO>> violations =
                validator.validate(dto);

        assertFalse(violations.isEmpty());
    }

    @Test
    void shouldPassWhenRequestIsValid() {

        FeasibilityRequestDTO dto =
                new FeasibilityRequestDTO(-23.55, -46.63, 100, 0, 10);

        Set<ConstraintViolation<FeasibilityRequestDTO>> violations =
                validator.validate(dto);

        assertTrue(violations.isEmpty());
    }
}