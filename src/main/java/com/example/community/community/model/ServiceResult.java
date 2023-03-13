package com.example.community.community.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResult {

    boolean result;
    String message;

    public ServiceResult() {
        result = true;
    }

    public ServiceResult(boolean result, String message){
        this.result = result;
        this.message = message;
    }
}
