package com.example.community.community.dto;

import com.example.community.community.entity.Posting;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
public class PostDto {
    long postNum;
    String title;
    String content;

    String userId;
    LocalDateTime createdAt;


    public static PostDto of(Posting posting) {
        return PostDto.builder()
                .postNum(posting.getPostNum())
                .title(posting.getTitle())
                .userId(posting.getUserId())
                .createdAt(posting.getCreatedAt())
                .build();
    }

    public String getCreatedDateText(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return createdAt != null ? createdAt.format(formatter) : "";
    }
}
