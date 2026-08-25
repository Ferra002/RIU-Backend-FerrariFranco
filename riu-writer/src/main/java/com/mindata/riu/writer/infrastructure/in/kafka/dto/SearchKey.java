package com.mindata.riu.writer.infrastructure.in.kafka.dto;

import jakarta.validation.constraints.NotBlank;

public record SearchKey(

    @NotBlank(message = "El campo 'searchId' no puede ser nulo o estar vacío")
    String searchId

) {}