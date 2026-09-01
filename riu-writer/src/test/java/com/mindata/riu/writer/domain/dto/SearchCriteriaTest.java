package com.mindata.riu.writer.domain.dto;

import com.mindata.riu.writer.domain.exception.search.CheckInAfterCheckOutException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchCriteriaTest {

    @Test
    void testValid(){
        SearchCriteria criteria = new SearchCriteria(
            "search-id",
            "abcde123",
            LocalDate.MIN,
            LocalDate.MAX,
            List.of(1,2,3)
        );

        assertNotNull(criteria);
    }

    @Test
    void testDoesNotThrowsOnNullCheckIn(){
        assertDoesNotThrow(() -> new SearchCriteria(
            "search-id",
            "abcde123",
            null,
            LocalDate.MAX,
            List.of(1,2,3)
        ));
    }

    @Test
    void testDoesNotThrowsOnNullCheckOut(){
        assertDoesNotThrow(() -> new SearchCriteria(
            "search-id",
            "abcde123",
            LocalDate.MIN,
            null,
            List.of(1,2,3)
        ));
    }

    @Test
    void testThrowsOnInvalidCheckInAndCheckOut(){
        LocalDate checkIn = LocalDate.MAX;
        LocalDate checkOut = LocalDate.MIN;
        List<Integer> ages = List.of(1,2,3);

        assertThrows(CheckInAfterCheckOutException.class, () -> {
            new SearchCriteria(
                "search-id",
                "abcde123",
                checkIn,
                checkOut,
                ages
            );
        });
    }

}