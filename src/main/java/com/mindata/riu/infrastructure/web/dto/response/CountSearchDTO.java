package com.mindata.riu.infrastructure.web.dto.response;

import java.time.LocalDate;
import java.util.List;

public record CountSearchDTO (

        String hotelId,
        LocalDate checkIn,
        LocalDate checkOut,
        List<Integer> ages

) {}
