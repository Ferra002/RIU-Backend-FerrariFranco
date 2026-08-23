package com.mindata.riu.application.port.out.repository;

import com.mindata.riu.application.port.out.dto.SearchRepositoryDTO;

import java.util.Optional;

public interface SearchRepository {

    Optional<SearchRepositoryDTO> findBySearchId(String searchId);

}
