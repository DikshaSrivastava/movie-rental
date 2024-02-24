package com.movie.movierentals.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * This is the response body when an exception is raised and handled.
 */
public class Error {

    private String message;
    private LocalDateTime timeStamp;
    private HttpStatus status;

    public Error(String message, LocalDateTime timeStamp, HttpStatus status) {
        super();
        this.message = message;
        this.timeStamp = timeStamp;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
