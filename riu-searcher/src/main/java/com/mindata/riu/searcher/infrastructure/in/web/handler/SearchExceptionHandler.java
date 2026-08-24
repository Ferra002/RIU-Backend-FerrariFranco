package com.mindata.riu.searcher.infrastructure.in.web.handler;

import com.mindata.riu.searcher.domain.exception.search.CheckInAfterCheckOutException;
import com.mindata.riu.searcher.domain.exception.search.InvalidSearchCriteriaException;
import com.mindata.riu.searcher.infrastructure.in.web.handler.dto.HandlerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SearchExceptionHandler {

    @ExceptionHandler(CheckInAfterCheckOutException.class)
    public ResponseEntity<HandlerResponse> handleCheckInAfterCheckOutException(CheckInAfterCheckOutException ex){
        HandlerResponse response = HandlerResponseBuilder.build(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(InvalidSearchCriteriaException.class)
    public ResponseEntity<HandlerResponse> handleInvalidSearchCriteriaException(InvalidSearchCriteriaException ex){
        HandlerResponse response = HandlerResponseBuilder.build(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
