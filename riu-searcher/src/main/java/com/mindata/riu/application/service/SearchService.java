package com.mindata.riu.application.service;

import com.mindata.riu.application.port.in.SearchUseCase;
import com.mindata.riu.application.port.out.event.SearchEventPublisher;
import com.mindata.riu.domain.exception.search.InvalidSearchCriteriaException;
import com.mindata.riu.domain.model.Search;
import com.mindata.riu.domain.model.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SearchService implements SearchUseCase {

    private final SearchEventPublisher eventPublisher;

    @Override
    public Search postSearch(SearchCriteria searchCriteria){
        if(searchCriteria == null) throw new InvalidSearchCriteriaException();

        String searchId = UUID.randomUUID().toString();
        eventPublisher.publish(searchId, searchCriteria);

        return new Search(searchId);
    }

}
