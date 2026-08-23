package com.mindata.riu.application.mapper;

import com.mindata.riu.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.application.port.out.dto.SearchResultDTO;
import com.mindata.riu.domain.model.SearchCount;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchRepositoryMapperTest {

    SearchRepositoryMapper mapper = Mappers.getMapper(SearchRepositoryMapper.class);

    @Test
    void toCount() {

        SearchRepositoryDTO dto = new SearchRepositoryDTO(
                "search-id",
                new SearchResultDTO(
                        "1234aBc",
                        LocalDate.MIN,
                        LocalDate.MAX,
                        List.of(1,2,3)
                ),
                100
        );

        SearchCount searchCount = mapper.toCount(dto);

        assertAll(
                "Verify mapped fields",
                () -> assertNotNull(searchCount),
                () -> assertNotNull(searchCount.searchId()),
                () -> assertNotNull(searchCount.search()),
                () -> assertNotNull(searchCount.search().hotelId()),
                () -> assertNotNull(searchCount.search().checkIn()),
                () -> assertNotNull(searchCount.search().checkOut()),
                () -> assertNotNull(searchCount.search().ages()),
                () -> assertNotNull(searchCount.count())
        );

    }
}