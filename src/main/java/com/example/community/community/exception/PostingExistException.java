package com.example.community.community.exception;

import com.example.community.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class PostingExistException extends AbstractException {
    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }

    @Override
    public String getMessage() {
        return "게시글이 존재하지 않습니다.";
    }
}
