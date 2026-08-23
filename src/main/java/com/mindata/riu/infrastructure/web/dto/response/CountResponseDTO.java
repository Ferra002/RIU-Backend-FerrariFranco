package com.mindata.riu.infrastructure.web.dto.response;

public record CountResponseDTO(

        String searchId,
        CountSearchDTO search,
        Integer count

) {}
