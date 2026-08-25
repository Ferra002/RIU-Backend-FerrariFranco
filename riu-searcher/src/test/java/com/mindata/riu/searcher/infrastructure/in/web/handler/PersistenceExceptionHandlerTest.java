package com.mindata.riu.searcher.infrastructure.in.web.handler;

import com.mindata.riu.searcher.application.exception.persistence.SearchNotFoundException;
import org.junit.jupiter.api.Test;

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