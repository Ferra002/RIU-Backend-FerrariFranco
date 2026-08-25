package com.mindata.riu.writer.factory;

import com.mindata.riu.writer.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.writer.domain.dto.SearchCriteria;
import com.mindata.riu.writer.infrastructure.in.kafka.dto.SearchEvent;
import com.mindata.riu.writer.infrastructure.in.kafka.dto.SearchKey;
import com.mindata.riu.writer.infrastructure.out.persistence.entity.SearchEntity;

import java.time.LocalDate;
import java.util.List;

public class TestClassBuilder {

    public static final SearchCriteria SEARCH_CRITERIA = new SearchCriteria(
        "search-id",
        "abcde123",
        LocalDate.MIN,
        LocalDate.MAX,
        List.of(1,2,3)
    );

    public static final SearchRepositoryDTO SEARCH_REPOSITORY_DTO = new SearchRepositoryDTO(
        "search-id",
        "abcde123",
        LocalDate.MIN,
        LocalDate.MAX,
        List.of(1,2,3)
    );

    public static final SearchKey SEARCH_KEY = new SearchKey("search-id");

    public static final SearchEvent SEARCH_EVENT = new SearchEvent(
        "abcd123",
        LocalDate.MIN,
        LocalDate.MAX,
        List.of(1,2,3)
    );

    public static final SearchEntity SEARCH_ENTITY = new SearchEntity(
        "search-id",
        "abcde123",
        LocalDate.MIN,
        LocalDate.MAX,
        List.of(1,2,3)
    );

}
