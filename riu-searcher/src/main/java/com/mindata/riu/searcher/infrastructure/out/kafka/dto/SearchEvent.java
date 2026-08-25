package com.mindata.riu.searcher.infrastructure.out.kafka.dto;

import java.time.LocalDate;
import java.util.List;

public record SearchEvent (

    String hotelId,
    LocalDate checkIn,
    LocalDate checkOut,
    List<Integer> ages

) {}