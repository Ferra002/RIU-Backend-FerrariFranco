package com.mindata.riu.searcher.infrastructure.in.web.dto.response;

public record HandlerResponse(

    int status,
    String error,
    String message,
    String path

) {}
