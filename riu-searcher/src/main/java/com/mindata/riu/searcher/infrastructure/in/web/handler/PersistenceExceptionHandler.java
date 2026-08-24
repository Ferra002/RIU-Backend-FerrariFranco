package com.mindata.riu.searcher.infrastructure.in.web.handler;

import com.mindata.riu.searcher.application.exception.persistence.SearchNotFoundException;
import com.mindata.riu.searcher.infrastructure.in.web.handler.dto.HandlerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersistenceExceptionHandler {

    @ExceptionHandler(SearchNotFoundException.class)
    public ResponseEntity<HandlerResponse> handleSearchNotFound(SearchNotFoundException ex){
        HandlerResponse response = HandlerResponseBuilder.build(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
