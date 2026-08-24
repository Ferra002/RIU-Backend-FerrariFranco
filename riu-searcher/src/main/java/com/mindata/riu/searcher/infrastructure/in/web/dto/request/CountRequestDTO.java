package com.mindata.riu.searcher.infrastructure.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CountRequestDTO(

        @NotBlank(message = "El campo 'searchId' no puede ser nulo o estar vacío")
        String searchId

) {}
