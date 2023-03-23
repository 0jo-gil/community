package com.example.community.member.exception;

import com.example.community.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class MemberNotExistException extends AbstractException {

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "존재하지 않는 아이디 입니다.";
    }
}
