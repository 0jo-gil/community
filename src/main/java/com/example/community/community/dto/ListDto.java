package com.example.community.community.dto;

import com.example.community.community.entity.Posting;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ListDto {

    String title;
    String content;

    String userId;

    LocalDateTime createdAt;

}
