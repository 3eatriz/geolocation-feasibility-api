package com.beatriz.geolocation_feasibility_api.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.beatriz.geolocation_feasibility_api.domain.Point;

@Repository
public class InMemoryFeasibilityRepository {

    private final List<Point> data = List.of(
            new Point(1L, "CTO-RJ-0004", -23.551000, -46.632000, "ACTIVE"),
            new Point(2L, "CTO-RJ-0005", -23.561000, -46.637000, "RESERVED")
    );

    public List<Point> findAll() {
        return data;
    }
}