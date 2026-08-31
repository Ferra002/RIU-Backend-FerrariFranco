package com.mindata.riu.searcher.infrastructure.in.web.request;

import com.mindata.riu.searcher.domain.exception.search.CheckInAfterCheckOutException;
import com.mindata.riu.searcher.infrastructure.in.web.dto.request.SearchRequestDTO;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.Arrays;
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

    @ParameterizedTest(name = "{0}")
    @CsvSource(value = {
            "Ages below Zero, valid-hotel, 2020-01-01, 2030-12-12, '1,2,-1'",
            "Null Hotel Id, null, 2020-01-01, 2030-12-12, '1,2,3'",
            "Blank Hotel Id, '', 2020-01-01, 2030-12-12, '1,2,3'",
            "Null Check In, valid-hotel, null, 2030-12-12, '1,2,3'",
            "Null Check Out, valid-hotel, 2020-01-01, null, '1,2,3'",
            "Null Ages, valid-hotel, 2020-01-01, 2030-12-12, null",
            "Empty Ages, valid-hotel, 2020-01-01, 2030-12-12, ''"
    }, nullValues = "null")
    void testInvalidSearchRequest(
            String caseName,
            String hotelId,
            LocalDate checkIn,
            LocalDate checkOut,
            String ages
    ){
        List<Integer> agesList;

        if(ages == null){
            agesList = null;
        } else if (ages.isBlank()){
            agesList = List.of();
        } else {
            agesList = Arrays.stream(ages.split(","))
                    .map(Integer::parseInt)
                    .toList();
        }

        SearchRequestDTO dto = new SearchRequestDTO(
                hotelId,
                checkIn,
                checkOut,
                agesList
        );

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }
}