package com.mindata.riu.infrastructure.in.web.dto.response;

public record CountResponseDTO(

        String searchId,
        CountSearchDTO search,
        Integer count

) {}
