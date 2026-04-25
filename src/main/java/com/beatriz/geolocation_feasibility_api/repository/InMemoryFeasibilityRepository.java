package com.beatriz.geolocation_feasibility_api.repository;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.beatriz.geolocation_feasibility_api.domain.Point;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Repository
public class InMemoryFeasibilityRepository {

    private List<Point> data;

    @PostConstruct
    public void init() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            InputStream inputStream = getClass()
                    .getResourceAsStream("/data/points.json");

            data = Arrays.asList(mapper.readValue(inputStream, Point[].class));

        } catch (Exception e) {
            throw new RuntimeException("Error loading dataset into memory", e);
        }
    }

    public List<Point> findAll() {
        return data;
    }
}