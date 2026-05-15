package com.fut_sexta.fut_sexta.exception;


import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MatchAlreadyFinishedException.class)
    public ResponseEntity<ErrorResponse> MatchAlreadyFinishedHandler(MatchAlreadyFinishedException ex){
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MatchNotFoundException.class)
    public ResponseEntity<ErrorResponse> MatchNotFoundHandler(MatchNotFoundException ex){
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NameAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> NameAlreadyExistsHandler(NameAlreadyExistsException ex){
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PlayerAlreadyInTeamException.class)
    public ResponseEntity<ErrorResponse> PlayerAlreadyInTeamHandler(PlayerAlreadyInTeamException ex){
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> PlayerNotFoundHandler(PlayerNotFoundException ex){
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlayerNotInTeamException.class)
    public ResponseEntity<ErrorResponse> PlayerNotInTeamHandler(PlayerNotInTeamException ex){
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TeamNotFoundException.class)
    public ResponseEntity<ErrorResponse> TeamNotFoundHandler(TeamNotFoundException ex){
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }



    private ResponseEntity<ErrorResponse> buildErrorResponse(String message, HttpStatus status){
        ErrorResponse error = new ErrorResponse(message, status, LocalDateTime.now());

        return ResponseEntity.status(status).body(error);
    }

}
