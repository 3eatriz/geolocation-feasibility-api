package com.beatriz.geolocation_feasibility_api.filter;

import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RequestResponseFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        String requestId = UUID.randomUUID().toString();

        response.setHeader("X-Request-Id", requestId);

        try {
            filterChain.doFilter(request, response);

        } finally {
            long endTime = System.currentTimeMillis();
            long responseTime = endTime - startTime;
            response.setHeader("X-Response-Time", responseTime + "ms");
            response.setHeader(
                    "Content-Type",
                    "application/json; charset=UTF-8"
            );
        }
    }
}