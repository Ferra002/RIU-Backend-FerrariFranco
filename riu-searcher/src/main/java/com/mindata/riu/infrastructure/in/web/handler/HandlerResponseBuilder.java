package com.mindata.riu.infrastructure.in.web.handler;

import com.mindata.riu.infrastructure.in.web.dto.response.HandlerResponse;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class HandlerResponseBuilder {

    public static HandlerResponse build(
            HttpStatus status,
            String message
    ){
        return new HandlerResponse(
            status.value(),
            status.getReasonPhrase(),
            message,
            LocalDateTime.now().toString()
        );
    }

}
