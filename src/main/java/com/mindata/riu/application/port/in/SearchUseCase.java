package com.mindata.riu.application.port.in;

import com.mindata.riu.domain.model.Search;
import com.mindata.riu.domain.model.SearchCriteria;

public interface SearchUseCase {

    Search search(SearchCriteria searchCriteria);

}
