package com.mindata.riu.searcher.infrastructure.in.web.dto.request;

import com.mindata.riu.searcher.domain.exception.search.CheckInAfterCheckOutException;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record SearchRequestDTO(

        @Schema(
            description = "Identificador del hotel",
            example = "1234aBc"
        )
        @NotBlank(message = "El campo 'hotelId' no puede ser nulo o estar vacío")
        String hotelId,

        @Schema(
            description = "Fecha del check-in, formato dd/MM/yyyy. NO puede ser una fecha pasada",
            example = "24/08/2026"
        )
        @FutureOrPresent(message = "El campo 'checkIn' no puede ser una fecha pasada")
        @NotNull(message = "El campo 'checkIn' no puede ser nulo o estar vacío")
        LocalDate checkIn,

        @Schema(
            description = "Fecha del check-out, formato dd/MM/yyyy",
            example = "27/08/2026"
        )
        @NotNull(message = "El campo 'checkOut' no puede ser nulo o estar vacío")
        LocalDate checkOut,

        @Schema(
            description = "Lista de edades de las personas involucradas. El valor mínimo es 0.",
            minLength = 0,
            minimum = "0",
            example = "[48,54,22]"
        )
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