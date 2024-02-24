package com.movie.movierentals.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * This class is the global exception handler. Exception that are thrown are handled here
 * and proper message and status code is set here which is sent in response.
 */
@ControllerAdvice
public class MovieRentalsExceptionHandler {

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Error> handleBadRequestException(BadRequestException exception) {
        Error error = new Error(exception.getMessage(), LocalDateTime.now(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
