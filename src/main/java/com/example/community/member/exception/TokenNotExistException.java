package com.example.community.member.exception;

import com.example.community.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class TokenNotExistException extends AbstractException {
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "JWT Token이 존재하지 않습니다.";
    }
}
