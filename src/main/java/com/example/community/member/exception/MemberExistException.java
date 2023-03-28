package com.example.community.member.exception;

import com.example.community.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class MemberExistException extends AbstractException {
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "이미 존재하는 아이디 입니다.";
    }
}
