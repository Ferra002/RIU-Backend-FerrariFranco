package com.mindata.riu.searcher.infrastructure.in.service;

import com.mindata.riu.searcher.application.port.out.event.SearchEventPublisher;
import com.mindata.riu.searcher.domain.exception.search.InvalidSearchCriteriaException;
import com.mindata.riu.searcher.domain.model.Search;
import com.mindata.riu.searcher.factory.TestClassBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    SearchEventPublisher eventPublisher;

    @InjectMocks
    SearchService service;

    @Test
    void postSearch() {
        Search search = service.postSearch(TestClassBuilder.SEARCH_CRITERIA);
        assertNotNull(search);
    }

    @Test
    void postSearchNullSearchCriteria(){
        assertThrows(InvalidSearchCriteriaException.class, () -> service.postSearch(null));
    }

}