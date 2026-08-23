package com.mindata.riu.infrastructure.web.handler;

import com.mindata.riu.domain.exception.search.SearchNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SearchExceptionHandler {

    @ExceptionHandler(SearchNotFoundException.class)
    public ResponseEntity<String> handleSearchNotFound(SearchNotFoundException ex){
        String response = HandlerResponse.buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
