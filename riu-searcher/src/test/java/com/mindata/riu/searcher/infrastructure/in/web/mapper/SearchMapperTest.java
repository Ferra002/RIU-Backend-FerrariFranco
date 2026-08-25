package com.mindata.riu.searcher.infrastructure.in.web.mapper;

import com.mindata.riu.searcher.domain.model.Search;
import com.mindata.riu.searcher.domain.model.SearchCriteria;
import com.mindata.riu.searcher.factory.TestClassBuilder;
import com.mindata.riu.searcher.infrastructure.in.web.dto.request.SearchRequestDTO;
import com.mindata.riu.searcher.infrastructure.in.web.dto.response.SearchResponseDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

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
    void toCriteriaNull(){
        assertNull(mapper.toCriteria(null));
    }

    @Test
    void toCriteriaNullAges(){
        SearchRequestDTO dto = new SearchRequestDTO(
            "5923Eba",
            LocalDate.MIN,
            LocalDate.MAX,
            null
        );
        SearchCriteria result = mapper.toCriteria(dto);

        assertNotNull(result);
        assertNull(result.ages());
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

    @Test
    void toResponseNull(){
        assertNull(mapper.toResponse(null));
    }

}