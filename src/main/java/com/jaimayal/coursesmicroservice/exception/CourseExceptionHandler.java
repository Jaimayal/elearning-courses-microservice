package com.jaimayal.coursesmicroservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CourseExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
    
    @ExceptionHandler(UserNotEnrolledException.class)
    public ResponseEntity<?> handleUserNotEnrolledException(UserNotEnrolledException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
