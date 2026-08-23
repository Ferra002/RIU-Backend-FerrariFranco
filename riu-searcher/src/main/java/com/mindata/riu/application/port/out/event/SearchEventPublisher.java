package com.mindata.riu.application.port.out.event;

import com.mindata.riu.domain.model.SearchCriteria;

public interface SearchEventPublisher {

    void publish(String searchId, SearchCriteria searchCriteria);

}
