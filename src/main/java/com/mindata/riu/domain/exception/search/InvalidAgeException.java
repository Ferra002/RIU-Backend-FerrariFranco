package com.mindata.riu.domain.exception.search;

public class InvalidAgeException extends SearchException {

    public InvalidAgeException(Integer age) {
        super(String.format("La edad '%s' no es válida.", age));
    }

}
