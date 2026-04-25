package com.beatriz.geolocation_feasibility_api.config;

import com.beatriz.geolocation_feasibility_api.domain.Point;
import com.beatriz.geolocation_feasibility_api.repository.InMemoryFeasibilityRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader {

    private final InMemoryFeasibilityRepository repository;
    private final ObjectMapper objectMapper;

    public DataLoader(InMemoryFeasibilityRepository repository,
            ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void load() {
        try {
            InputStream inputStream = getClass()
                    .getResourceAsStream("/data/points.json");

            if (inputStream == null) {
                throw new RuntimeException("Dataset file not found in resources");
            }

            List<Point> points = objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<Point>>() {
                    });

            repository.saveAll(points);

            System.out.println("Dataset loaded: " + points.size() + " records");

        } catch (Exception e) {
            throw new RuntimeException("Failed to load dataset into memory", e);
        }
    }
}