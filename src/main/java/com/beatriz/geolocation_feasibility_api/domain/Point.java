package com.beatriz.geolocation_feasibility_api.domain;

public class Point {
    private Long id;
    private String nome;
    private Double latitude;
    private Double longitude;
    private String status;

    public Point(Long id, String nome, Double latitude, Double longitude, String status) {
        this.id = id;
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getStatus() {
        return status;
    }
}
