package com.mindata.riu.searcher.infrastructure.in.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

public record CountSearchDTO (

    @Schema(
        description = "Identificador del hotel",
        example = "1234aBc"
    )
    String hotelId,

    @Schema(
        description = "Fecha del check-in, formato yyyy-MM-dd",
        example = "2026-08-24"
    )
    LocalDate checkIn,

    @Schema(
        description = "Fecha del check-out, formato yyyy-MM-dd",
        example = "2026-08-27"
    )
    LocalDate checkOut,

    @Schema(
        description = "Lista de edades de las personas involucradas. El valor mínimo es 0.",
        minLength = 0,
        minimum = "0",
        example = "[48,54,22]"
    )
    List<Integer> ages

) {}
