package com.mindata.riu.searcher.infrastructure.in.web.dto.response;

public record CountResponseDTO(

        String searchId,
        CountSearchDTO search,
        Integer count

) {}
