package com.jaimayal.coursesmicroservice.exception;

public class CourseNotFoundException extends RuntimeException {
    
    public CourseNotFoundException(String message) {
        super(message);
    }
}
