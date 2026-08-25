package com.mindata.riu.writer.infrastructure.out.persistence.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchEntityTest {

    @Test
    void createSearchEntity(){
        SearchEntity entity = new SearchEntity(
            "search-id",
            "abcde123",
            LocalDate.MIN,
            LocalDate.MAX,
            List.of(1,2,3)
        );

        assertNotNull(entity);
    }

}