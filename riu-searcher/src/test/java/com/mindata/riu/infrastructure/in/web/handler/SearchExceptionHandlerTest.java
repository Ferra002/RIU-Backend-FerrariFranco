package com.mindata.riu.infrastructure.in.web.handler;

import com.mindata.riu.domain.exception.search.SearchNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SearchExceptionHandlerTest {

    SearchExceptionHandler searchExceptionHandler = new SearchExceptionHandler();

    @Test
    void handleSearchNotFound() {
        var response = searchExceptionHandler.handleSearchNotFound(new SearchNotFoundException("sample-id"));

        assertAll(
            () -> assertTrue(response.getStatusCode().is4xxClientError()),
            () -> assertNotNull(response.getBody()),
            () -> assertFalse(Objects.requireNonNull(response.getBody()).isEmpty())
        );
    }

}