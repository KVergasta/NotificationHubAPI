package com.SpringNotificationHub.NotificationServ.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    
    ErrorDetails errorDetails = new ErrorDetails();
    
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)   
    public ResponseEntity<?> channelNotFoundHandler(){
        
        errorDetails.setMessage("Some field is null or invalid.");
        errorDetails.setTimestamp(new java.sql.Date(System.currentTimeMillis()));

        return ResponseEntity
        .status(HttpStatus.NOT_ACCEPTABLE)
        .header("Exception", FieldNotAccetableException.class.getSimpleName())
        .header("Error", HttpStatus.NOT_ACCEPTABLE.name())
        .body(errorDetails.getMessage());
    }
    
    @ExceptionHandler(org.springframework.dao.EmptyResultDataAccessException.class)
    public ResponseEntity<?> emptyResultHandler(){
        errorDetails.setMessage("Resource not found. Please check the ID and try again.");
        errorDetails.setTimestamp(new java.sql.Date(System.currentTimeMillis()));

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .header("Exception", "EmptyResultDataAccessException")
            .header("Error", HttpStatus.NOT_FOUND.name())
            .body(errorDetails.getMessage());
    }

    @ExceptionHandler(Exception.class)   
    public ResponseEntity<?> internalErrorhandler(){

        errorDetails.setMessage("Internal server error." +"\n" + "Please try again later.");
        errorDetails.setTimestamp(new java.sql.Date(System.currentTimeMillis()));
        
        return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .header("Exception", NotFoundException.class.getSimpleName())
        .header("Error", HttpStatus.INTERNAL_SERVER_ERROR.name())
        .body(errorDetails.getMessage());            
    }
    
    @ExceptionHandler(NotFoundException.class)   
    public ResponseEntity<?> notFoundHandler(){        
        errorDetails.setMessage("Resource not found." +"\n" + "Please check the request and try again.");
        errorDetails.setTimestamp(new java.sql.Date(System.currentTimeMillis()));
        return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .header("Exception", NotFoundException.class.getSimpleName())
        .header("Error", HttpStatus.NOT_FOUND.name())
        .body(errorDetails.getMessage());            
    }
    
}
