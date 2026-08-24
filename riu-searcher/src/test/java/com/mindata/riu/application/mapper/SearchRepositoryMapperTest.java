package com.mindata.riu.application.mapper;

import com.mindata.riu.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.domain.model.SearchCount;
import com.mindata.riu.domain.model.SearchCriteria;
import com.mindata.riu.factory.TestClassBuilder;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class SearchRepositoryMapperTest {

    SearchRepositoryMapper mapper = Mappers.getMapper(SearchRepositoryMapper.class);

    @Test
    void toSearchCriteria() {
        SearchRepositoryDTO dto = TestClassBuilder.SEARCH_REPOSITORY_DTO;
        SearchCriteria mapped = mapper.toSearchCriteria(dto);

        assertAll(
                "Verify mapped fields",
                () -> assertNotNull(mapped),
                () -> assertNotNull(mapped.hotelId()),
                () -> assertEquals(mapped.hotelId(), dto.hotelId()),
                () -> assertNotNull(mapped.checkIn()),
                () -> assertEquals(mapped.checkIn(), dto.checkIn()),
                () -> assertNotNull(mapped.checkOut()),
                () -> assertEquals(mapped.checkOut(), dto.checkOut()),
                () -> assertNotNull(mapped.ages()),
                () -> assertEquals(mapped.ages(), dto.ages())
        );
    }
}