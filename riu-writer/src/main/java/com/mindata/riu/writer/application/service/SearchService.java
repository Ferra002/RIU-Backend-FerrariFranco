package com.mindata.riu.writer.application.service;

import com.mindata.riu.writer.application.mapper.SearchRepositoryMapper;
import com.mindata.riu.writer.application.port.in.ProcessSearchUseCase;
import com.mindata.riu.writer.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.writer.application.port.out.repository.SearchRepository;
import com.mindata.riu.writer.domain.dto.SearchCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService implements ProcessSearchUseCase {

    private final SearchRepository repository;
    private final SearchRepositoryMapper mapper;

    @Override
    @Transactional
    public void process(SearchCriteria searchCriteria) {
        SearchRepositoryDTO saved = repository.save(mapper.toDto(searchCriteria));
        log.info("Saved search with searchId: '{}'", saved.searchId());
    }

}
