package com.mindata.riu.searcher.infrastructure.in.web.handler;

import com.mindata.riu.searcher.infrastructure.in.web.handler.dto.HandlerResponse;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class HandlerResponseBuilder {

    private HandlerResponseBuilder(){
    }

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
