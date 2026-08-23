package com.mindata.riu.application.port.out.dto;

public record SearchRepositoryDTO (

        String searchId,
        SearchResultDTO search,
        Integer count

) {}
