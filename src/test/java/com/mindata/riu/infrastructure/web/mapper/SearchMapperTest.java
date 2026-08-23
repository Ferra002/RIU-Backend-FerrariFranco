package com.mindata.riu.infrastructure.web.mapper;

import com.mindata.riu.domain.model.Search;
import com.mindata.riu.domain.model.SearchCriteria;
import com.mindata.riu.factory.TestClassBuilder;
import com.mindata.riu.infrastructure.web.dto.request.SearchRequestDTO;
import com.mindata.riu.infrastructure.web.dto.response.SearchResponseDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class SearchMapperTest {

    SearchMapper mapper = Mappers.getMapper(SearchMapper.class);

    @Test
    void toCriteria() {
        SearchRequestDTO dto = TestClassBuilder.SEARCH_REQUEST_DTO;
        SearchCriteria mapped = mapper.toCriteria(dto);

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

    @Test
    void toResponse() {
        Search search = TestClassBuilder.SEARCH;
        SearchResponseDTO mapped = mapper.toResponse(search);

        assertAll(
                "Verify mapped fields",
                () -> assertNotNull(mapped),
                () -> assertNotNull(mapped.searchId()),
                () -> assertEquals(mapped.searchId(), search.searchId())
        );
    }
}