package com.mindata.riu.searcher.infrastructure.out.persistence.adapter;

import com.mindata.riu.searcher.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.searcher.application.port.out.repository.SearchRepository;
import com.mindata.riu.searcher.domain.model.SearchCriteria;
import com.mindata.riu.searcher.infrastructure.out.persistence.mapper.SearchPersistenceMapper;
import com.mindata.riu.searcher.infrastructure.out.persistence.repository.JpaSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
            repository.findAllByHotelIdAndCheckInEqualsAndCheckOutEquals(
                searchCriteria.hotelId(),
                searchCriteria.checkIn(),
                searchCriteria.checkOut()
            ).stream()
            .filter(entity -> entity.getAges().equals(searchCriteria.ages()))
            .count()
        );
    }

}
