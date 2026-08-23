package com.mindata.riu.factory;

import com.mindata.riu.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.application.port.out.dto.SearchResultDTO;
import com.mindata.riu.domain.model.Search;
import com.mindata.riu.domain.model.SearchCount;
import com.mindata.riu.domain.model.SearchCriteria;
import com.mindata.riu.infrastructure.web.dto.request.CountRequestDTO;
import com.mindata.riu.infrastructure.web.dto.request.SearchRequestDTO;

import java.time.LocalDate;
import java.util.List;

public class TestClassBuilder {

    public static final SearchRepositoryDTO SEARCH_REPOSITORY_DTO = new SearchRepositoryDTO(
            "search-id",
            new SearchResultDTO(
                    "1234aBc",
                    LocalDate.MIN,
                    LocalDate.MAX,
                    List.of(1,2,3)
            ),
            100
    );

    public static final SearchCount SEARCH_COUNT = new SearchCount(
            "search-id",
            new SearchCriteria(
                    "4321cBa",
                    LocalDate.MIN,
                    LocalDate.MAX,
                    List.of(4,5,6)
            ),
            100
    );

    public static final CountRequestDTO COUNT_REQUEST_DTO = new CountRequestDTO("search-id");

    public static final SearchRequestDTO SEARCH_REQUEST_DTO = new SearchRequestDTO(
            "5923Eba",
            LocalDate.MIN,
            LocalDate.MAX,
            List.of(7,8,9)
    );

    public static final Search SEARCH = new Search(
            "search-id"
    );

    public static final SearchCriteria SEARCH_CRITERIA = new SearchCriteria(
        "valid-hotel",
        LocalDate.MIN,
        LocalDate.MAX,
        List.of(1,2,3)
    );

}
