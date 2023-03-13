package com.example.community.community.exception;

public class PostingExistException extends RuntimeException {
    public PostingExistException(String error){
        super(error);
    }
}
