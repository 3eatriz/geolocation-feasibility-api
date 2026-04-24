package com.beatriz.geolocation_feasibility_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.beatriz.geolocation_feasibility_api.dto.FeasibilityRequestDTO;
import com.beatriz.geolocation_feasibility_api.dto.FeasibilityResponseDTO;
import com.beatriz.geolocation_feasibility_api.repository.InMemoryFeasibilityRepository;

@Service
public class FeasibilityService {

    private final InMemoryFeasibilityRepository repository;

    public FeasibilityService(InMemoryFeasibilityRepository repository) {
        this.repository = repository;
    }

    public List<FeasibilityResponseDTO> execute(FeasibilityRequestDTO request) {

        return repository.findAll().stream()
                .filter(e -> e.getStatus().equals("ACTIVE") || e.getStatus().equals("RESERVED"))
                .map(e -> {
                    double radius = calculateradius(
                            request.latitude(),
                            request.longitude(),
                            e.getLatitude(),
                            e.getLongitude()
                    );

                    return new FeasibilityResponseDTO(
                            e.getId(),
                            e.getNome(),
                            e.getLatitude(),
                            e.getLongitude(),
                            radius
                    );
                })
                .filter(r -> r.radius() <= request.radius())
                .sorted((a, b) -> Double.compare(a.radius(), b.radius()))
                .skip((long) request.page() * request.size())
                .limit(request.size())
                .toList();
    }

    private double calculateradius(double lat1, double lon1, double lat2, double lon2) {

        final int R = 6371000;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2)
                        + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2)
                        * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}