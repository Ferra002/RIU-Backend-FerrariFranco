package com.mindata.riu.writer.domain.dto;

import com.mindata.riu.writer.domain.exception.search.CheckInAfterCheckOutException;

import java.time.LocalDate;
import java.util.List;

public record SearchCriteria(

    String searchId,
    String hotelId,
    LocalDate checkIn,
    LocalDate checkOut,
    List<Integer> ages

) {

    public SearchCriteria{
        if(
            checkIn != null &&
            checkOut != null &&
            checkOut.isBefore(checkIn)
        ) throw new CheckInAfterCheckOutException(checkIn, checkOut);
    }

}
