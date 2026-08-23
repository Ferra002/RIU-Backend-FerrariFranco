package com.mindata.riu.application.service;

import com.mindata.riu.application.port.in.SearchUseCase;
import com.mindata.riu.domain.model.Search;
import com.mindata.riu.domain.model.SearchCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService implements SearchUseCase {

    @Override
    public Search search(SearchCriteria searchCriteria){
        String searchId = UUID.randomUUID().toString();

        // Send Kafka message
        log.info("Simulating Kafka message...");

        return new Search(searchId);
    }

}
