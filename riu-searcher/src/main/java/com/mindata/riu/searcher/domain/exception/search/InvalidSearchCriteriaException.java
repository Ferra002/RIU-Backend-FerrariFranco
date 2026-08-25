package com.mindata.riu.searcher.domain.exception.search;

import com.mindata.riu.searcher.domain.exception.RiuBaseException;

public class InvalidSearchCriteriaException extends RiuBaseException {

    public InvalidSearchCriteriaException() {
        super("Search Criteria no puede ser nulo");
    }

}
