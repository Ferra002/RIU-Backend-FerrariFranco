package com.mindata.riu.searcher.infrastructure.in.web.handler.dto;

public record HandlerResponse(

    int status,
    String error,
    String message,
    String path

) {}
