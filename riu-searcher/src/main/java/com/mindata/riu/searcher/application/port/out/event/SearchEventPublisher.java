package com.mindata.riu.searcher.application.port.out.event;

import com.mindata.riu.searcher.domain.model.SearchCriteria;

public interface SearchEventPublisher {

    void publish(String searchId, SearchCriteria searchCriteria);

}
