package com.mindata.riu.searcher.infrastructure.out.persistence.adapter;

import com.mindata.riu.searcher.factory.TestClassBuilder;
import com.mindata.riu.searcher.infrastructure.out.persistence.entity.SearchEntity;
import com.mindata.riu.searcher.infrastructure.out.persistence.mapper.SearchPersistenceMapper;
import com.mindata.riu.searcher.infrastructure.out.persistence.repository.JpaSearchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchRepositoryAdapterTest {

    @Mock
    JpaSearchRepository repository;

    @Mock
    SearchPersistenceMapper mapper;

    @InjectMocks
    SearchRepositoryAdapter adapter;

    @Test
    void findBySearchId() {
        String searchId = "search-id";
        var entity = TestClassBuilder.SEARCH_ENTITY;
        var expected = TestClassBuilder.SEARCH_REPOSITORY_DTO;

        when(repository.findBySearchId(searchId)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(expected);

        var result = adapter.findBySearchId(searchId);

        assertAll(
            () -> assertTrue(result.isPresent()),
            () -> assertEquals(Optional.of(expected), result)
        );
    }

    @Test
    void findBySearchIdNotFound() {
        String searchId = "search-id";

        when(repository.findBySearchId(searchId)).thenReturn(Optional.empty());

        var result = adapter.findBySearchId(searchId);
        assertTrue(result.isEmpty());
    }

    @Test
    void countEqualSearches() {
        var searchCriteria = TestClassBuilder.SEARCH_CRITERIA;
        var list = new ArrayList<>(List.of(TestClassBuilder.SEARCH_ENTITY, TestClassBuilder.SEARCH_ENTITY));
        Integer expectedResult = list.size();

        list.add(new SearchEntity(
            0L,
            "search-id",
            "hotel-id",
            LocalDate.MIN,
            LocalDate.MAX,
            list.getFirst().getAges().reversed()
        ));

        when(repository.findAllByHotelIdAndCheckInEqualsAndCheckOutEquals(
            searchCriteria.hotelId(), searchCriteria.checkIn(), searchCriteria.checkOut()
        )).thenReturn(list);

        Integer result = adapter.countEqualSearches(searchCriteria);

        assertEquals(expectedResult, result);
    }

    @Test
    void countEqualSearchesEmpty() {
        var searchCriteria = TestClassBuilder.SEARCH_CRITERIA;
        List<SearchEntity> list = List.of();

        when(repository.findAllByHotelIdAndCheckInEqualsAndCheckOutEquals(
            searchCriteria.hotelId(), searchCriteria.checkIn(), searchCriteria.checkOut()
        )).thenReturn(list);

        Integer result = adapter.countEqualSearches(searchCriteria);

        assertEquals(0, result);
    }
}