package com.beatriz.geolocation_feasibility_api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean; // Novo pacote no Spring 4/Boot 4
import org.springframework.test.web.servlet.MockMvc;

import com.beatriz.geolocation_feasibility_api.service.FeasibilityService;

@SpringBootTest
@AutoConfigureMockMvc
class FeasibilityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // No Spring Boot 4.x, @MockBean foi substituída por @MockitoBean
    @MockitoBean 
    private FeasibilityService service;

    @Test
    void shouldReturnFeasibilityResults() throws Exception {
        when(service.execute(any())).thenReturn(List.of());

        mockMvc.perform(get("/api/feasibility")
                .param("latitude", "-23.55")
                .param("longitude", "-46.63")
                .param("radius", "100")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenValidationFails() throws Exception {
        mockMvc.perform(get("/api/feasibility")
                .param("latitude", "200") // Valor inválido para disparar @Max/@Min ou Bean Validation
                .param("longitude", "-46.63")
                .param("radius", "100"))
                .andExpect(status().isBadRequest());
    }
}
