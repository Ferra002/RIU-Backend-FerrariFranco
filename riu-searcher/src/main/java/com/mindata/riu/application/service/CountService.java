package com.mindata.riu.application.service;

import com.mindata.riu.application.mapper.SearchRepositoryMapper;
import com.mindata.riu.application.port.in.CountUseCase;
import com.mindata.riu.application.port.out.repository.SearchRepository;
import com.mindata.riu.domain.exception.search.SearchNotFoundException;
import com.mindata.riu.domain.model.SearchCount;
import com.mindata.riu.domain.model.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountService implements CountUseCase {

    private final SearchRepository repository;
    private final SearchRepositoryMapper mapper;

    @Override
    public SearchCount count(String searchId) {
        SearchCriteria searchCriteria = mapper.toSearchCriteria(
                repository.findBySearchId(searchId)
                        .orElseThrow(() -> new SearchNotFoundException(searchId))
        );

        return new SearchCount(
            searchId,
            searchCriteria,
            repository.countEqualSearches(searchCriteria)
        );
    }

}
