package com.mindata.riu.searcher.application.mapper;

import com.mindata.riu.searcher.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.searcher.domain.model.SearchCriteria;
import com.mindata.riu.searcher.factory.TestClassBuilder;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

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

    @Test
    void toSearchCriteriaNull(){
        assertNull(mapper.toSearchCriteria(null));
    }

    @Test
    void toSearchCriteriaNullAge(){
        SearchRepositoryDTO dto = new SearchRepositoryDTO(
            "search-id",
            "1234aBc",
            LocalDate.MIN,
            LocalDate.MAX,
            null
        );
        SearchCriteria criteria = mapper.toSearchCriteria(dto);

        assertNotNull(criteria);
        assertNull(criteria.ages());
    }

}