package com.example.community.community.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long postNum;
    String userId;
    long viewCount;
    String title;
    String content;
    String hashtag;
    long likeCount;
    LocalDateTime createdAt;
    LocalDateTime lastUpdatedAt;
    boolean notMemberShowYn;
}