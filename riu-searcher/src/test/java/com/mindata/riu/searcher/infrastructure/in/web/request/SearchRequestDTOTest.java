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
import java.time.format.DateTimeFormatter;
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
            "Ages below Zero, valid-hotel, 01/01/3000, 04/01/3000, '1,2,-1'",
            "Null Hotel Id, null, 01/01/3000, 04/01/3000, '1,2,3'",
            "Blank Hotel Id, '', 01/01/3000, 04/01/3000, '1,2,3'",
            "Null Check In, valid-hotel, null, 04/01/3000, '1,2,3'",
            "Past Check In, valid-hotel, 01/01/2000, 04/01/3000, '1,2,3'",
            "Null Check Out, valid-hotel, 01/01/3000, null, '1,2,3'",
            "Null Ages, valid-hotel, 01/01/3000, 04/01/3000, null",
            "Empty Ages, valid-hotel, 01/01/3000, 04/01/3000, ''"
    }, nullValues = "null")
    void testInvalidSearchRequest(
            String caseName,
            String hotelId,
            String rawCheckIn,
            String rawCheckOut,
            String rawAges
    ){
        List<Integer> ages;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

        LocalDate checkIn = rawCheckIn != null ? LocalDate.parse(rawCheckIn, formatter) : null;
        LocalDate checkOut = rawCheckOut != null ? LocalDate.parse(rawCheckOut, formatter) : null;


        if(rawAges == null){
            ages = null;
        } else if (rawAges.isBlank()){
            ages = List.of();
        } else {
            ages = Arrays.stream(rawAges.split(","))
                    .map(Integer::parseInt)
                    .toList();
        }

        SearchRequestDTO dto = new SearchRequestDTO(
                hotelId,
                checkIn,
                checkOut,
                ages
        );

        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }
}