package com.mindata.riu.writer.infrastructure.in.kafka.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record SearchEvent(

    @NotBlank(message = "El campo 'hotelId' no puede ser nulo o estar vacío")
    String hotelId,

    @NotNull(message = "El campo 'checkIn' no puede ser nulo")
    LocalDate checkIn,

    @NotNull(message = "El campo 'checkOut' no puede ser nulo")
    LocalDate checkOut,

    @NotNull(message = "El campo 'ages' no puede ser nulo")
    @NotEmpty(message = "El campo 'ages' no puede estar vacío")
    List<
        @NotNull(message = "El valor dentro de 'ages' no puede ser nulo")
        @Min(value = 0, message = "El valor dentro de 'ages' no puede ser menor a 0")
        Integer
    > ages

) {}
