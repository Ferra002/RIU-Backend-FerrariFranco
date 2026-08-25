package com.mindata.riu.searcher.application.port.in;

import com.mindata.riu.searcher.domain.model.Search;
import com.mindata.riu.searcher.domain.model.SearchCriteria;

public interface SearchUseCase {

    Search postSearch(SearchCriteria searchCriteria);

}
