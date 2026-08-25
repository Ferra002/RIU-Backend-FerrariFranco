package com.mindata.riu.searcher.infrastructure.in.web.handler;

import com.mindata.riu.searcher.domain.exception.search.CheckInAfterCheckOutException;
import com.mindata.riu.searcher.domain.exception.search.InvalidSearchCriteriaException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SearchExceptionHandlerTest {

    SearchExceptionHandler exceptionHandler = new SearchExceptionHandler();

    @Test
    void handleCheckInAfterCheckOutException() {
        var response = exceptionHandler.handleCheckInAfterCheckOutException(
            new CheckInAfterCheckOutException(LocalDate.MAX, LocalDate.MIN)
        );

        assertAll(
            () -> assertTrue(response.getStatusCode().is4xxClientError()),
            () -> assertNotNull(response.getBody())
        );
    }

    @Test
    void handleInvalidSearchCriteriaException(){
        var response = exceptionHandler.handleInvalidSearchCriteriaException(
            new InvalidSearchCriteriaException()
        );

        assertAll(
            () -> assertTrue(response.getStatusCode().is5xxServerError()),
            () -> assertNotNull(response.getBody())
        );
    }

}