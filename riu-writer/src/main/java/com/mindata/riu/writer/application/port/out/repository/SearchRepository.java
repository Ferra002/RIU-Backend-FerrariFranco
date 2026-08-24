package com.mindata.riu.writer.application.port.out.repository;

import com.mindata.riu.writer.application.port.out.dto.SearchRepositoryDTO;

public interface SearchRepository {

    SearchRepositoryDTO save(SearchRepositoryDTO searchCriteria);

}
