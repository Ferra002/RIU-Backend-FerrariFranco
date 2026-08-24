package com.mindata.riu.writer.infrastructure.out.persistence.adapter;

import com.mindata.riu.writer.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.writer.application.port.out.repository.SearchRepository;
import com.mindata.riu.writer.infrastructure.out.persistence.mapper.SearchPersistenceMapper;
import com.mindata.riu.writer.infrastructure.out.persistence.repository.JpaSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchRepositoryAdapter implements SearchRepository {

    private final JpaSearchRepository repository;
    private final SearchPersistenceMapper mapper;

    @Override
    public SearchRepositoryDTO save(SearchRepositoryDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
}
