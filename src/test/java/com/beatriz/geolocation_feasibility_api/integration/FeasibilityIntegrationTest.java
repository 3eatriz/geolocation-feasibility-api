package com.beatriz.geolocation_feasibility_api.integration;

import com.beatriz.geolocation_feasibility_api.dto.FeasibilityRequestDTO;
import com.beatriz.geolocation_feasibility_api.repository.InMemoryFeasibilityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FeasibilityIntegrationTest {

    @Autowired
    private com.beatriz.geolocation_feasibility_api.service.FeasibilityService service;

    @Autowired
    private InMemoryFeasibilityRepository repository;

    @Test
    void shouldExecuteFullFlow() {

        FeasibilityRequestDTO request =
                new FeasibilityRequestDTO(-23.55, -46.63, 500, 0, 10);

        var result = service.execute(request);

        assertNotNull(result);
    }
}