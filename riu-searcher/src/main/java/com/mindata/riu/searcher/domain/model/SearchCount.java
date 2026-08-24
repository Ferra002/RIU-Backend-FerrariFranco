package com.mindata.riu.searcher.domain.model;

public record SearchCount(

        String searchId,
        SearchCriteria search,
        Integer count

) {}
