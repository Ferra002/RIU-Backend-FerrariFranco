package com.mindata.riu.writer.infrastructure.out.persistence.adapter;

import com.mindata.riu.writer.application.port.out.dto.SearchRepositoryDTO;
import com.mindata.riu.writer.factory.TestClassBuilder;
import com.mindata.riu.writer.infrastructure.out.persistence.entity.SearchEntity;
import com.mindata.riu.writer.infrastructure.out.persistence.mapper.SearchPersistenceMapper;
import com.mindata.riu.writer.infrastructure.out.persistence.repository.JpaSearchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchRepositoryAdapterTest {

    @Mock
    private JpaSearchRepository repository;

    @Mock
    private SearchPersistenceMapper mapper;

    @InjectMocks
    private SearchRepositoryAdapter adapter;

    @Test
    void save() {
        SearchRepositoryDTO dto = TestClassBuilder.SEARCH_REPOSITORY_DTO;
        SearchEntity entity = TestClassBuilder.SEARCH_ENTITY;

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        SearchRepositoryDTO result = adapter.save(dto);

        verify(mapper).toEntity(dto);
        verify(repository).save(entity);
        verify(mapper).toDto(entity);

        assertNotNull(result);
    }
}