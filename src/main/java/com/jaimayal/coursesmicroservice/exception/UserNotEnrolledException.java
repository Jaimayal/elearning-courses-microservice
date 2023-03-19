package com.jaimayal.coursesmicroservice.exception;

public class UserNotEnrolledException extends RuntimeException {
    
    public UserNotEnrolledException(String message) {
        super(message);
    }
}
