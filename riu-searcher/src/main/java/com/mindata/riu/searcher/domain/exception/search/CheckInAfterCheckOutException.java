package com.mindata.riu.searcher.domain.exception.search;

import com.mindata.riu.searcher.domain.exception.RiuBaseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CheckInAfterCheckOutException extends RiuBaseException {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public CheckInAfterCheckOutException(LocalDate checkIn, LocalDate checkOut) {
        super(String.format(
                "La fecha de check-in '%s' no puede ser posterior al check-out '%s'",
                checkIn.format(FORMATTER),
                checkOut.format(FORMATTER)
        ));
    }

}
