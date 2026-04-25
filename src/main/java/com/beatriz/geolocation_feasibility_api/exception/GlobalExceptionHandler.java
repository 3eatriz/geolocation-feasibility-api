package com.beatriz.geolocation_feasibility_api.exception;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.beatriz.geolocation_feasibility_api.dto.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            BindException.class
    })
    public ResponseEntity<ErrorResponseDTO> handleValidation(Exception ex) {

        String message;

        if (ex instanceof MethodArgumentNotValidException validationEx) {
            message = validationEx.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
        } else if (ex instanceof BindException bindEx) {
            message = bindEx.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
        } else {
            message = "validation error";
        }

        ErrorResponseDTO error = new ErrorResponseDTO(
                "400",
                "empty field",
                message,
                "bad request",
                Instant.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneral(Exception ex) {

        ErrorResponseDTO error = new ErrorResponseDTO(
                "500",
                "internal server error",
                "general fail",
                "internal server error",
                Instant.now()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}