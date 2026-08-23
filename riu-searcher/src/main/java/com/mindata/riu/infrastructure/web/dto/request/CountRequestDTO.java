package com.mindata.riu.infrastructure.web.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CountRequestDTO(

        @NotBlank
        String searchId

) {}
