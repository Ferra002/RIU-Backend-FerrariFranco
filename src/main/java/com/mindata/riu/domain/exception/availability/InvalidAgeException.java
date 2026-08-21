package com.mindata.riu.domain.exception.availability;

public class InvalidAgeException extends AvailabilityException {

    public InvalidAgeException(Integer age) {
        super(String.format("La edad '%s' no es válida.", age));
    }

}
