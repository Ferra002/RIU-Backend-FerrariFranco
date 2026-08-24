package com.mindata.riu.application.exception.persistence;

import com.mindata.riu.domain.exception.RiuBaseException;

public class SearchNotFoundException extends RiuBaseException {

    public SearchNotFoundException(String searchId) {
        super(String.format("No se encontró una búsqueda con ID '%s'", searchId));
    }

}
