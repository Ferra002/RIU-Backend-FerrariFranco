package com.mindata.riu.searcher.infrastructure.in.web.mapper;

import com.mindata.riu.searcher.domain.model.SearchCount;
import com.mindata.riu.searcher.domain.model.SearchCriteria;
import com.mindata.riu.searcher.factory.TestClassBuilder;
import com.mindata.riu.searcher.infrastructure.in.web.dto.response.CountResponseDTO;
import com.mindata.riu.searcher.infrastructure.in.web.mapper.CountMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountMapperTest {

    CountMapper mapper = Mappers.getMapper(CountMapper.class);

    @Test
    void toResponse() {
        SearchCount searchCount = TestClassBuilder.SEARCH_COUNT;
        CountResponseDTO mapped = mapper.toResponse(searchCount);

        assertAll(
                "Verify mapped fields",
                () -> assertNotNull(mapped),
                () -> assertNotNull(mapped.searchId()),
                () -> assertEquals(mapped.searchId(), searchCount.searchId()),
                () -> assertNotNull(mapped.search()),
                () -> assertNotNull(mapped.search().hotelId()),
                () -> assertEquals(mapped.search().hotelId(), searchCount.search().hotelId()),
                () -> assertNotNull(mapped.search().checkIn()),
                () -> assertEquals(mapped.search().checkIn(), searchCount.search().checkIn()),
                () -> assertNotNull(mapped.search().checkOut()),
                () -> assertEquals(mapped.search().checkOut(), searchCount.search().checkOut()),
                () -> assertNotNull(mapped.search().ages()),
                () -> assertEquals(mapped.search().ages(), searchCount.search().ages()),
                () -> assertNotNull(mapped.count()),
                () -> assertEquals(mapped.count(), searchCount.count())
        );
    }

    @Test
    void toResponseNull(){
        assertNull(mapper.toResponse(null));
    }

    @Test
    void toResponseNullCriteria(){
        var response = mapper.toResponse(new SearchCount(
            "search-id",
            null,
            100
        ));

        assertNotNull(response);
        assertNull(response.search());
    }

    @Test
    void toResponseNullCriteriaAge(){
        var response = mapper.toResponse(new SearchCount(
            "search-id",
            new SearchCriteria(
                "4321cBa",
                LocalDate.MIN,
                LocalDate.MAX,
                null
            ),
            100
        ));

        assertNotNull(response);
        assertNotNull(response.search());
        assertNull(response.search().ages());
    }

}