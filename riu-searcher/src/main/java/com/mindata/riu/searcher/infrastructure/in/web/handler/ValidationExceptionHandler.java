package com.mindata.riu.searcher.infrastructure.in.web.handler;

import com.mindata.riu.searcher.infrastructure.in.web.handler.dto.HandlerResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HandlerResponse> handleValidationExceptions(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .findFirst()
            .map(FieldError::getDefaultMessage)
            .orElse("Error de validación");

        HandlerResponse response = HandlerResponseBuilder.build(HttpStatus.BAD_REQUEST, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<HandlerResponse> handleConstraintViolationException(ConstraintViolationException ex){
        String message = ex.getConstraintViolations()
            .stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.joining(", "));

        HandlerResponse response = HandlerResponseBuilder.build(HttpStatus.BAD_REQUEST, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HandlerResponse> handleMessageNotReadableException(HttpMessageNotReadableException ex){
        HandlerResponse response = HandlerResponseBuilder.build(
            HttpStatus.BAD_REQUEST,
            "El cuerpo de la solicitud es inválido o está ausente"
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<HandlerResponse> handleMissingRequestParameterException(MissingServletRequestParameterException ex){
        HandlerResponse response = HandlerResponseBuilder.build(
            HttpStatus.BAD_REQUEST,
            String.format("El parámetro '%s' es obligatorio", ex.getParameterName())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
