package com.mindata.riu.application.mapper;

import com.mindata.riu.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.application.port.out.dto.SearchResultDTO;
import com.mindata.riu.domain.model.SearchCount;
import com.mindata.riu.factory.TestClassBuilder;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchRepositoryMapperTest {

    SearchRepositoryMapper mapper = Mappers.getMapper(SearchRepositoryMapper.class);

    @Test
    void toCount() {
        SearchRepositoryDTO dto = TestClassBuilder.SEARCH_REPOSITORY_DTO;
        SearchCount mapped = mapper.toCount(dto);

        assertAll(
                "Verify mapped fields",
                () -> assertNotNull(mapped),
                () -> assertNotNull(mapped.searchId()),
                () -> assertEquals(mapped.searchId(), dto.searchId()),
                () -> assertNotNull(mapped.search()),
                () -> assertNotNull(mapped.search().hotelId()),
                () -> assertEquals(mapped.search().hotelId(), dto.search().hotelId()),
                () -> assertNotNull(mapped.search().checkIn()),
                () -> assertEquals(mapped.search().checkIn(), dto.search().checkIn()),
                () -> assertNotNull(mapped.search().checkOut()),
                () -> assertEquals(mapped.search().checkOut(), dto.search().checkOut()),
                () -> assertNotNull(mapped.search().ages()),
                () -> assertEquals(mapped.search().ages(), dto.search().ages()),
                () -> assertNotNull(mapped.count()),
                () -> assertEquals(mapped.count(), dto.count())
        );
    }
}