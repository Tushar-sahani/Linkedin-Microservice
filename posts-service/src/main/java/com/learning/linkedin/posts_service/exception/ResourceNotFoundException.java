package com.learning.linkedin.posts_service.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(){

    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
