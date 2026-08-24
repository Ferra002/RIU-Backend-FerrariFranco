package com.mindata.riu.searcher.infrastructure.in.web.dto.request;

import com.mindata.riu.searcher.domain.exception.search.CheckInAfterCheckOutException;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record SearchRequestDTO(

        @NotBlank(message = "El campo 'hotelId' no puede ser nulo o estar vacío")
        String hotelId,

        @NotNull(message = "El campo 'checkIn' no puede ser nulo o estar vacío")
        LocalDate checkIn,

        @NotNull(message = "El campo 'checkOut' no puede ser nulo o estar vacío")
        LocalDate checkOut,

        @NotNull(message = "El campo 'ages' no puede ser nulo")
        @NotEmpty(message = "El campo 'ages' no debe estar vacío")
        List<
            @NotNull(message = "El valor dentro de 'ages' no puede ser nulo")
            @Min(value = 0, message = "El valor dentro de 'ages' no puede ser menor a 0")
            Integer
        > ages

) {

    public SearchRequestDTO {
        if(checkIn != null && checkOut != null &&checkIn.isAfter(checkOut))
            throw new CheckInAfterCheckOutException(checkIn, checkOut);

    }

}