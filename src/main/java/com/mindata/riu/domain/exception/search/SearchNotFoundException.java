package com.mindata.riu.domain.exception.search;

public class SearchNotFoundException extends RuntimeException {
    public SearchNotFoundException(String searchId) {
        super(String.format("No se encontró una búsqueda con ID '%s'", searchId));
    }
}
