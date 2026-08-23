package com.mindata.riu.domain.exception.search;

import com.mindata.riu.domain.exception.RiuBaseException;

public class InvalidSearchCriteriaException extends RiuBaseException {

    public InvalidSearchCriteriaException() {
        super("Search Criteria no puede ser nulo");
    }

}
