package com.mindata.riu.searcher.infrastructure.out.persistence.adapter;

import com.mindata.riu.searcher.domain.repository.dto.SearchRepositoryDTO;
import com.mindata.riu.searcher.domain.repository.SearchRepository;
import com.mindata.riu.searcher.domain.model.SearchCriteria;
import com.mindata.riu.searcher.infrastructure.out.persistence.mapper.SearchPersistenceMapper;
import com.mindata.riu.searcher.infrastructure.out.persistence.repository.JpaSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SearchRepositoryAdapter implements SearchRepository {

    private final JpaSearchRepository repository;
    private final SearchPersistenceMapper mapper;

    @Override
    public Optional<SearchRepositoryDTO> findBySearchId(String searchId) {
        return repository.findBySearchId(searchId).map(mapper::toDto);
    }

    @Override
    public Integer countEqualSearches(SearchCriteria searchCriteria) {
        return Math.toIntExact(
            repository.countByHotelIdAndCheckInEqualsAndCheckOutEqualsAndRawAgesEquals(
                searchCriteria.hotelId(),
                searchCriteria.checkIn(),
                searchCriteria.checkOut(),
                searchCriteria.ages().stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(","))
            )
        );
    }

}
