package com.mindata.riu.application.port.out.repository;

import com.mindata.riu.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.domain.model.SearchCriteria;

import java.util.Optional;

public interface SearchRepository {

    Optional<SearchRepositoryDTO> findBySearchId(String searchId);

    Integer countEqualSearches(SearchCriteria searchCriteria);

}
