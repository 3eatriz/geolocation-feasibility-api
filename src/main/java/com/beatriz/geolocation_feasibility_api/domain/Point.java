package com.beatriz.geolocation_feasibility_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Point {

    private Long id;
    private String name;
    private String type;
    private String status;
    private Integer capacity;
    private Integer occupancy;
    private Double utilizationPct;

    private List<Geometry> geometry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(Integer occupancy) {
        this.occupancy = occupancy;
    }

    public Double getUtilizationPct() {
        return utilizationPct;
    }

    public void setUtilizationPct(Double utilizationPct) {
        this.utilizationPct = utilizationPct;
    }

    public List<Geometry> getGeometry() {
        return geometry;
    }

    public void setGeometry(List<Geometry> geometry) {
        this.geometry = geometry;
    }
}