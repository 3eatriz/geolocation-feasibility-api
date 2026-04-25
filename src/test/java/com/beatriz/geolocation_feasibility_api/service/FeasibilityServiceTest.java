package com.beatriz.geolocation_feasibility_api.service;

import com.beatriz.geolocation_feasibility_api.domain.Geometry;
import com.beatriz.geolocation_feasibility_api.domain.Point;
import com.beatriz.geolocation_feasibility_api.dto.FeasibilityRequestDTO;
import com.beatriz.geolocation_feasibility_api.dto.FeasibilityResponseDTO;
import com.beatriz.geolocation_feasibility_api.repository.InMemoryFeasibilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FeasibilityServiceTest {

    @Mock
    private InMemoryFeasibilityRepository repository;

    @InjectMocks
    private FeasibilityService service;

    private List<Point> mockPoints;

    @BeforeEach
    void setup() {

        Point p1 = new Point();
        p1.setId(1L);
        p1.setName("Point A");
        p1.setStatus("ACTIVE");

        Geometry g1 = new Geometry();
        g1.setX(-46.63);
        g1.setY(-23.55);
        p1.setGeometry(List.of(g1));

        Point p2 = new Point();
        p2.setId(2L);
        p2.setName("Point B");
        p2.setStatus("INACTIVE"); // deve ser filtrado

        Geometry g2 = new Geometry();
        g2.setX(-46.64);
        g2.setY(-23.56);
        p2.setGeometry(List.of(g2));

        mockPoints = List.of(p1, p2);
    }

    @Test
    void shouldReturnOnlyActivePointsInsideRadius() {

        when(repository.findAll()).thenReturn(mockPoints);

        FeasibilityRequestDTO request =
                new FeasibilityRequestDTO(-23.55, -46.63, 5000, 0, 10);

        List<FeasibilityResponseDTO> result = service.execute(request);

        assertEquals(1, result.size());
        assertEquals("Point A", result.get(0).nome());
    }

    @Test
    void shouldReturnEmptyWhenNoPointsMatchRadius() {

        when(repository.findAll()).thenReturn(mockPoints);

        FeasibilityRequestDTO request =
                new FeasibilityRequestDTO(0.0, 0.0, 10, 0, 10);

        List<FeasibilityResponseDTO> result = service.execute(request);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldApplyPaginationCorrectly() {

        when(repository.findAll()).thenReturn(mockPoints);

        FeasibilityRequestDTO request =
                new FeasibilityRequestDTO(-23.55, -46.63, 5000, 0, 1);

        List<FeasibilityResponseDTO> result = service.execute(request);

        assertEquals(1, result.size());
    }
}