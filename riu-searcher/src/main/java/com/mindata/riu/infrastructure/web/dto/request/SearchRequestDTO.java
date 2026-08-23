package com.mindata.riu.infrastructure.web.dto.request;

import com.mindata.riu.domain.exception.search.CheckInAfterCheckOutException;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record SearchRequestDTO(

        @NotBlank
        String hotelId,

        @NotNull
        LocalDate checkIn,

        @NotNull
        LocalDate checkOut,

        @NotNull
        @NotEmpty
        List<@NotNull @Min(0) Integer> ages

) {

    public SearchRequestDTO {
        if(checkIn != null && checkOut != null &&checkIn.isAfter(checkOut))
            throw new CheckInAfterCheckOutException(checkIn, checkOut);

    }

}