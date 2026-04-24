package com.beatriz.geolocation_feasibility_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beatriz.geolocation_feasibility_api.dto.FeasibilityRequestDTO;
import com.beatriz.geolocation_feasibility_api.dto.FeasibilityResponseDTO;
import com.beatriz.geolocation_feasibility_api.service.FeasibilityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/feasibility")
public class FeasibilityController {
    private final FeasibilityService feasibilityService;

    public FeasibilityController(FeasibilityService feasibilityService) {
        this.feasibilityService = feasibilityService;
    }

    @GetMapping
    public List<FeasibilityResponseDTO> check(@Valid @ModelAttribute FeasibilityRequestDTO request) {
        return feasibilityService.execute(request);
    }
}
