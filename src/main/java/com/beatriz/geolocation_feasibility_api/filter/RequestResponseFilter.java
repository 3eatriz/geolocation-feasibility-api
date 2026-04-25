package com.beatriz.geolocation_feasibility_api.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class RequestResponseFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RequestResponseFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();
        String requestId = UUID.randomUUID().toString();

        // REQUEST LOG
        log.info("Incoming request | id={} | method={} | uri={}",
                requestId,
                request.getMethod(),
                request.getRequestURI()
        );

        response.setHeader("X-Request-Id", requestId);

        try {
            filterChain.doFilter(request, response);
        } finally {

            long responseTime = System.currentTimeMillis() - startTime;

            response.setHeader("X-Response-Time", responseTime + "ms");

            // RESPONSE LOG
            log.info("Completed request | id={} | status={} | time={}ms",
                    requestId,
                    response.getStatus(),
                    responseTime
            );
        }
    }
}