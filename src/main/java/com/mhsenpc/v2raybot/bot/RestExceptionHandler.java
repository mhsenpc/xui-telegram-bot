package com.mhsenpc.v2raybot.bot;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.http.converter.HttpMessageNotReadableException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    // This method will handle deserialization errors (HttpMessageNotReadableException)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        // Create a custom error message that you want to return to the client
        String errorMessage = "Invalid request body. Error: " + ex.getMessage();

        // You can log the exception or do more processing if needed
        ex.printStackTrace(); // For debugging purposes

        // Return a ResponseEntity with a custom message and status 400 (Bad Request)
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}


