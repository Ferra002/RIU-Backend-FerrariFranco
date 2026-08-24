package com.mindata.riu.infrastructure.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CountRequestDTO(

        @NotBlank
        String searchId

) {}
