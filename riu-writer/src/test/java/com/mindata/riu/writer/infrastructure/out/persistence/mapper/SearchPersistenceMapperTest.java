package com.mindata.riu.writer.infrastructure.out.persistence.mapper;

import com.mindata.riu.writer.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.writer.factory.TestClassBuilder;
import com.mindata.riu.writer.infrastructure.out.persistence.entity.SearchEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchPersistenceMapperTest {

    SearchPersistenceMapper mapper = Mappers.getMapper(SearchPersistenceMapper.class);

    @Test
    void toEntity() {
        SearchRepositoryDTO dto = TestClassBuilder.SEARCH_REPOSITORY_DTO;
        SearchEntity result = mapper.toEntity(dto);

        assertAll(
            () -> assertNotNull(result),
            () -> assertNull(result.getId()),
            () -> assertNotNull(result.getSearchId()),
            () -> assertEquals(dto.searchId(), result.getSearchId()),
            () -> assertNotNull(result.getHotelId()),
            () -> assertEquals(dto.hotelId(), result.getHotelId()),
            () -> assertNotNull(result.getCheckIn()),
            () -> assertEquals(dto.checkIn(), result.getCheckIn()),
            () -> assertNotNull(result.getCheckOut()),
            () -> assertEquals(dto.checkOut(), result.getCheckOut()),
            () -> assertNotNull(result.getRawAges()),
            () -> assertFalse(result.getRawAges().isBlank()),
            () -> assertNotNull(result.getAges()),
            () -> assertEquals(dto.ages(), result.getAges())
        );
    }

    @Test
    void toEntityNull(){
        assertNull(mapper.toEntity(null));
    }

    @Test
    void toEntityNullAge() {
        SearchRepositoryDTO dto = new SearchRepositoryDTO(
            "search-id",
            "abcde123",
            LocalDate.MIN,
            LocalDate.MAX,
            null
        );
        SearchEntity result = mapper.toEntity(dto);

        assertAll(
            () -> assertNotNull(result),
            () -> assertNull(result.getAges())
        );
    }

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
            "search-id",
            "abcde123",
            LocalDate.MIN,
            LocalDate.MAX,
            "1,2,3",
            null
        );
        SearchRepositoryDTO result = mapper.toDto(entity);

        assertAll(
            () -> assertNotNull(result),
            () -> assertNull(result.ages())
        );
    }

    @Test
    void toRawAges(){
        String expected = "1,2,3";
        List<Integer> ages = List.of(1,2,3);

        String result = mapper.toRawAges(ages);

        assertEquals(expected, result);
    }

    @Test
    void toRawAgesEmptyAges(){
        String result = mapper.toRawAges(List.of());

        assertAll(
                () -> assertNotNull(result),
                () -> assertTrue(result.isBlank())
        );
    }

    @Test
    void toRawAgesNullAges(){
        assertNull(mapper.toRawAges(null));
    }

}