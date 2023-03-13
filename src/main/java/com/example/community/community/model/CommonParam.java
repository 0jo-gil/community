package com.example.community.community.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonParam {
    int pageIndex;
    long pageSize;

    public long getPageStart(){
        init();
        return (pageIndex - 1) * pageSize;
    }

    public long getPageEnd(){
        init();
        return pageSize;
    }

    public void init(){
        if(pageIndex < 1){
            pageIndex = 1;
        }

        if(pageSize < 10){
            pageSize = 10;
        }
    }

//    public String getQueryString(){
//
//        StringBuilder sb = new StringBuilder();
//
//        if()
//    }

}
