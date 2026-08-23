package com.mindata.riu.domain.model;

public record SearchCount(

        String searchId,
        SearchCriteria search,
        Integer count

) {}
