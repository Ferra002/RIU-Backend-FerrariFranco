package com.mindata.riu.domain.model;

public record Count (

        String searchId,
        SearchCriteria search,
        Integer count

) {}
