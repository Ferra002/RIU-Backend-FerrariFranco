package com.mindata.riu.searcher.application.service;

import com.mindata.riu.searcher.application.mapper.SearchRepositoryMapper;
import com.mindata.riu.searcher.application.port.in.CountUseCase;
import com.mindata.riu.searcher.application.port.out.repository.SearchRepository;
import com.mindata.riu.searcher.application.exception.persistence.SearchNotFoundException;
import com.mindata.riu.searcher.domain.model.SearchCount;
import com.mindata.riu.searcher.domain.model.SearchCriteria;
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
