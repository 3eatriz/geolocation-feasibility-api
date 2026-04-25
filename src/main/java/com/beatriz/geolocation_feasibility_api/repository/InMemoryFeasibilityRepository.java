package com.beatriz.geolocation_feasibility_api.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.beatriz.geolocation_feasibility_api.domain.Point;

@Repository
public class InMemoryFeasibilityRepository {

    private List<Point> data = List.of();

    public List<Point> findAll() {
        return data;
    }

    public void saveAll(List<Point> points) {
        this.data = List.copyOf(points);
    }
}