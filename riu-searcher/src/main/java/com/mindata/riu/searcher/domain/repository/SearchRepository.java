package com.mindata.riu.searcher.domain.repository;

import com.mindata.riu.searcher.domain.repository.dto.SearchRepositoryDTO;
import com.mindata.riu.searcher.domain.model.SearchCriteria;

import java.util.Optional;

public interface SearchRepository {

    Optional<SearchRepositoryDTO> findBySearchId(String searchId);

    Integer countEqualSearches(SearchCriteria searchCriteria);

}
