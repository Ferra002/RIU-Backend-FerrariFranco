package com.mindata.riu.searcher.application.exception.persistence;

import com.mindata.riu.searcher.domain.exception.RiuBaseException;

public class SearchNotFoundException extends RiuBaseException {

    public SearchNotFoundException(String searchId) {
        super(String.format("No se encontró una búsqueda con ID '%s'", searchId));
    }

}
