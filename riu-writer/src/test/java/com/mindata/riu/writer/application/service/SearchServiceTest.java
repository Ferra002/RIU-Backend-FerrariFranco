package com.mindata.riu.writer.application.service;

import com.mindata.riu.writer.application.mapper.SearchRepositoryMapper;
import com.mindata.riu.writer.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.writer.application.port.out.repository.SearchRepository;
import com.mindata.riu.writer.domain.dto.SearchCriteria;
import com.mindata.riu.writer.factory.TestClassBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    SearchRepository repository;

    @Mock
    SearchRepositoryMapper mapper;

    @InjectMocks
    SearchService service;

    @Test
    void process() {
        SearchCriteria criteria = TestClassBuilder.SEARCH_CRITERIA;
        SearchRepositoryDTO dto = TestClassBuilder.SEARCH_REPOSITORY_DTO;

        when(mapper.toDto(criteria)).thenReturn(dto);
        when(repository.save(dto)).thenReturn(dto);

        service.process(criteria);

        verify(mapper).toDto(criteria);
        verify(repository).save(dto);
    }
}