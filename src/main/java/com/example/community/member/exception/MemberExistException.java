package com.example.community.member.exception;

public class MemberExistException extends RuntimeException {
    public MemberExistException (String error){
        super(error);
    }
}
