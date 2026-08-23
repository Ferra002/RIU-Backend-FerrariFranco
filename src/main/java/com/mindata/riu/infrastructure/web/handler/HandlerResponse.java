package com.mindata.riu.infrastructure.web.handler;

import org.springframework.http.HttpStatus;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class HandlerResponse {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String buildResponse(
            HttpStatus status,
            String message
    ){
        Map<String, Object> response = new LinkedHashMap<>();

        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", message);
        response.put("timestamp", LocalDateTime.now().toString());

        return objectMapper.writeValueAsString(response);
    }

}
