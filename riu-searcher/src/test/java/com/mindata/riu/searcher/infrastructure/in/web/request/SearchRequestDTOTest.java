package com.mindata.riu.searcher.infrastructure.in.web.request;

import com.mindata.riu.searcher.domain.exception.search.CheckInAfterCheckOutException;
import com.mindata.riu.searcher.infrastructure.in.web.dto.request.SearchRequestDTO;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setup(){
        try (var factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void validDTO() {
        assertDoesNotThrow(() -> {
            new SearchRequestDTO(
                    "valid-hotel",
                    LocalDate.MIN,
                    LocalDate.MAX,
                    List.of(1,2,3)
            );
        });
    }

    @Test
    void checkOutBeforeCheckIn(){
        LocalDate checkIn = LocalDate.MAX;
        LocalDate checkOut = LocalDate.MIN;
        List<Integer> ages = List.of(1,2,3);

        assertThrows(CheckInAfterCheckOutException.class,
                () -> new SearchRequestDTO(
                        "valid-hotel",
                        checkIn,
                        checkOut,
                        ages
                ));
    }

    @Test
    void agesBelowZero(){
        var dto = new SearchRequestDTO(
                "valid-hotel",
                LocalDate.MIN,
                LocalDate.MAX,
                List.of(1,2,-1)
        );

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }

    @Test
    void nullHotelId() {
        var dto = new SearchRequestDTO(
                null,
                LocalDate.MIN,
                LocalDate.MAX,
                List.of(1,2,-1)
        );

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }

    @Test
    void blankHotelId() {
        var dto = new SearchRequestDTO(
                "",
                LocalDate.MIN,
                LocalDate.MAX,
                List.of(1,2,-1)
        );

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }

    @Test
    void nullCheckIn() {
        var dto = new SearchRequestDTO(
                "valid-hotel",
                null,
                LocalDate.MAX,
                List.of(1,2,-1)
        );

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }

    @Test
    void nullCheckOut() {
        var dto = new SearchRequestDTO(
                "valid-hotel",
                LocalDate.MIN,
                null,
                List.of(1,2,-1)
        );

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }


    @Test
    void nullAges() {
        var dto = new SearchRequestDTO(
                "valid-hotel",
                LocalDate.MIN,
                LocalDate.MAX,
                null
        );

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }

    @Test
    void emptyAges() {
        var dto = new SearchRequestDTO(
                "valid-hotel",
                LocalDate.MIN,
                LocalDate.MAX,
                List.of()
        );

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }
}