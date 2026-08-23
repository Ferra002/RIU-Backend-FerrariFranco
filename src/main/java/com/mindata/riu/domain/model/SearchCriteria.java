package com.mindata.riu.domain.model;

import java.time.LocalDate;
import java.util.List;

public record SearchCriteria (

        String hotelId,
        LocalDate checkIn,
        LocalDate checkOut,
        List<Integer> ages

) {}