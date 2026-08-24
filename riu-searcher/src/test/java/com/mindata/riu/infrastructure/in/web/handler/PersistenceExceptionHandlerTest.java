package com.mindata.riu.infrastructure.in.web.handler;

import com.mindata.riu.application.exception.persistence.SearchNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class PersistenceExceptionHandlerTest {

    PersistenceExceptionHandler persistenceExceptionHandler = new PersistenceExceptionHandler();

    @Test
    void handleSearchNotFound() {
        var response = persistenceExceptionHandler.handleSearchNotFound(new SearchNotFoundException("sample-id"));

        assertAll(
            () -> assertTrue(response.getStatusCode().is4xxClientError()),
            () -> assertNotNull(response.getBody())
        );
    }

}