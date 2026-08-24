package com.mindata.riu.searcher.infrastructure.out.persistence.mapper;

import com.mindata.riu.searcher.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.searcher.factory.TestClassBuilder;
import com.mindata.riu.searcher.infrastructure.out.persistence.entity.SearchEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchPersistenceMapperTest {

    SearchPersistenceMapper mapper = Mappers.getMapper(SearchPersistenceMapper.class);

    @Test
    void toDto() {
        SearchEntity entity = TestClassBuilder.SEARCH_ENTITY;
        SearchRepositoryDTO result = mapper.toDto(entity);

        assertAll(
            () -> assertNotNull(result),
            () -> assertNotNull(result.searchId()),
            () -> assertEquals(entity.getSearchId(), result.searchId()),
            () -> assertNotNull(result.hotelId()),
            () -> assertEquals(entity.getHotelId(), result.hotelId()),
            () -> assertNotNull(result.checkIn()),
            () -> assertEquals(entity.getCheckIn(), result.checkIn()),
            () -> assertNotNull(result.checkOut()),
            () -> assertEquals(entity.getCheckOut(), result.checkOut()),
            () -> assertNotNull(result.ages()),
            () -> assertFalse(result.ages().isEmpty()),
            () -> assertEquals(entity.getAges(), result.ages())
        );
    }

    @Test
    void toDtoNull(){
        assertNull(mapper.toDto(null));
    }

    @Test
    void toDtoNullAge(){
        SearchEntity entity = new SearchEntity(
            0L,
            "search-id",
            "hotel-id",
            LocalDate.MIN,
            LocalDate.MAX,
            null
        );
        SearchRepositoryDTO result = mapper.toDto(entity);

        assertNotNull(result);
        assertNull(result.ages());
    }

}