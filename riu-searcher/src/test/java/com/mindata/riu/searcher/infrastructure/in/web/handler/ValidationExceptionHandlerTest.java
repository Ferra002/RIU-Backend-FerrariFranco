package com.mindata.riu.searcher.infrastructure.in.web.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidationExceptionHandlerTest {

    @Mock
    private MethodParameter parameter;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private ConstraintViolation<?> constraintViolation;

    @InjectMocks
    private ValidationExceptionHandler exceptionHandler;

    @Test
    void testHandleValidationExceptions() {
        var response = exceptionHandler.handleValidationExceptions(
            new MethodArgumentNotValidException(parameter, bindingResult)
        );

        assertAll(
            () -> assertNotNull(response),
            () -> assertTrue(response.getStatusCode().is4xxClientError()),
            () -> assertNotNull(response.getBody())
        );
    }

    @Test
    void testHandleConstraintViolationException(){
        String message = "El valor no puede ser nulo";

        when(constraintViolation.getMessage()).thenReturn(message);

        var response = exceptionHandler.handleConstraintViolationException(
            new ConstraintViolationException(Set.of(constraintViolation))
        );

        assertAll(
            () -> assertNotNull(response),
            () -> assertTrue(response.getStatusCode().is4xxClientError()),
            () -> assertNotNull(response.getBody()),
            () -> assertEquals(message, Objects.requireNonNull(response.getBody()).message())
        );
    }
}