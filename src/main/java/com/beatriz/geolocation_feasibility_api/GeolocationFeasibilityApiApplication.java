package com.beatriz.geolocation_feasibility_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.beatriz.geolocation_feasibility_api")
public class GeolocationFeasibilityApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeolocationFeasibilityApiApplication.class, args);
	}

}
