package com.mindata.riu.writer.application.mapper;

import com.mindata.riu.writer.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.writer.domain.dto.SearchCriteria;
import com.mindata.riu.writer.factory.TestClassBuilder;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class SearchRepositoryMapperTest {

    SearchRepositoryMapper mapper = Mappers.getMapper(SearchRepositoryMapper.class);

    @Test
    void toDto() {
        SearchCriteria expected = TestClassBuilder.SEARCH_CRITERIA;
        SearchRepositoryDTO result = mapper.toDto(expected);

        assertAll(
            () -> assertNotNull(result),
            () -> assertEquals(expected.searchId(), result.searchId()),
            () -> assertEquals(expected.hotelId(), result.hotelId()),
            () -> assertEquals(expected.checkIn(), result.checkIn()),
            () -> assertEquals(expected.checkOut(), result.checkOut()),
            () -> assertEquals(expected.ages(), result.ages())
        );
    }
}