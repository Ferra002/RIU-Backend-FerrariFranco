package com.mindata.riu.searcher.infrastructure.web.dto.request;

import com.mindata.riu.searcher.infrastructure.in.web.dto.request.CountRequestDTO;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountRequestDTOTest {

    private static Validator validator;

    @BeforeAll
    static void setup(){
        try (var factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void valid(){
        var dto = new CountRequestDTO("valid-search-id");
        var violations = validator.validate(dto);

        assertTrue(violations.isEmpty());
    }

    @Test
    void nullSearchId() {
        var dto = new CountRequestDTO(null);
        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }

    @Test
    void blankSearchId(){
        var dto = new CountRequestDTO("");
        var violations = validator.validate(dto);

        assertFalse(violations.isEmpty());
    }

}