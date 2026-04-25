package com.beatriz.geolocation_feasibility_api.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.beatriz.geolocation_feasibility_api.domain.Geometry;
import com.beatriz.geolocation_feasibility_api.dto.FeasibilityRequestDTO;
import com.beatriz.geolocation_feasibility_api.dto.FeasibilityResponseDTO;
import com.beatriz.geolocation_feasibility_api.repository.InMemoryFeasibilityRepository;

@Service
public class FeasibilityService {

        private final InMemoryFeasibilityRepository repository;

        public FeasibilityService(InMemoryFeasibilityRepository repository) {
                this.repository = repository;
        }

        private record PointDistance(
                        Long id,
                        String name,
                        Double latitude,
                        Double longitude,
                        Double distanceMeters) {
        }

        public List<FeasibilityResponseDTO> execute(FeasibilityRequestDTO request) {

                int page = (request.page() == null || request.page() < 0) ? 0 : request.page();
                int size = (request.size() == null || request.size() <= 0) ? 20 : Math.min(request.size(), 20);

                List<PointDistance> processed = repository.findAll()
                                .stream()
                                // 1. Filtra status válido
                                .filter(point -> point.getStatus() != null &&
                                                (point.getStatus().equals("ACTIVE")
                                                                || point.getStatus().equals("RESERVED")))
                                // 2. Valida geometria
                                .filter(point -> point.getGeometry() != null &&
                                                !point.getGeometry().isEmpty())
                                // 3. Remove coordenadas nulas
                                .filter(point -> {
                                        Geometry geo = point.getGeometry().get(0);
                                        return geo.getX() != null && geo.getY() != null;
                                })
                                // 4. Calcula distância
                                .map(point -> {
                                        Geometry geo = point.getGeometry().get(0);

                                        double distance = calculateDistanceMeters(
                                                        request.latitude(),
                                                        request.longitude(),
                                                        geo.getY(),
                                                        geo.getX());

                                        return new PointDistance(
                                                        point.getId(),
                                                        point.getName(),
                                                        geo.getY(),
                                                        geo.getX(),
                                                        distance);
                                })
                                // 5. Filtra pelo raio
                                .filter(p -> p.distanceMeters() <= request.radius())
                                // 6. Ordena por menor distância
                                .sorted(Comparator.comparingDouble(PointDistance::distanceMeters))
                                .toList();

                // 7. Paginação manual
                int start = page * size;
                int end = Math.min(start + size, processed.size());

                if (start >= processed.size()) {
                        return List.of();
                }

                return processed.subList(start, end)
                                .stream()
                                .map(p -> new FeasibilityResponseDTO(
                                                p.id(),
                                                p.name(),
                                                p.latitude(),
                                                p.longitude(),
                                                p.distanceMeters()))
                                .toList();
        }

        /**
         * Haversine em metros
         */
        private double calculateDistanceMeters(
                        Double lat1,
                        Double lon1,
                        Double lat2,
                        Double lon2) {
                if (lat1 == null || lon1 == null || lat2 == null || lon2 == null) {
                        throw new IllegalArgumentException("Invalid coordinates for distance calculation");
                }

                final int EARTH_RADIUS_KM = 6371;

                double latDistance = Math.toRadians(lat2 - lat1);
                double lonDistance = Math.toRadians(lon2 - lon1);

                double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                                + Math.cos(Math.toRadians(lat1))
                                                * Math.cos(Math.toRadians(lat2))
                                                * Math.sin(lonDistance / 2)
                                                * Math.sin(lonDistance / 2);

                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

                return EARTH_RADIUS_KM * c * 1000; // metros
        }
}