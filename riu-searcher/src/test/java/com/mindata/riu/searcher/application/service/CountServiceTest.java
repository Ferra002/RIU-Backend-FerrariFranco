package com.mindata.riu.searcher.application.service;

import com.mindata.riu.searcher.application.mapper.SearchRepositoryMapper;
import com.mindata.riu.searcher.application.port.out.repository.SearchRepository;
import com.mindata.riu.searcher.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.searcher.domain.model.SearchCount;
import com.mindata.riu.searcher.domain.model.SearchCriteria;
import com.mindata.riu.searcher.factory.TestClassBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountServiceTest {

    @Mock
    SearchRepository repository;

    @Mock
    SearchRepositoryMapper mapper;

    @InjectMocks
    CountService countService;

    @Test
    void count() {
        SearchRepositoryDTO searchRepositoryDto = TestClassBuilder.SEARCH_REPOSITORY_DTO;
        SearchCriteria expected = TestClassBuilder.SEARCH_CRITERIA;

        when(repository.findBySearchId(searchRepositoryDto.searchId())).thenReturn(Optional.of(searchRepositoryDto));
        when(mapper.toSearchCriteria(searchRepositoryDto)).thenReturn(expected);

        SearchCount result = countService.count(searchRepositoryDto.searchId());
        assertAll(
                () -> assertNotNull(result),
                () -> verify(repository).findBySearchId(searchRepositoryDto.searchId()),
                () -> verify(mapper).toSearchCriteria(searchRepositoryDto)
        );
    }
}