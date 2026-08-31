package com.mindata.riu.searcher.domain.repository.dto;

import java.time.LocalDate;
import java.util.List;

public record SearchRepositoryDTO (

    String searchId,
    String hotelId,
    LocalDate checkIn,
    LocalDate checkOut,
    List<Integer>ages

) {}
