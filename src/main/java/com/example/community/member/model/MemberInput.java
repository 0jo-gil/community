package com.example.community.member.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
public class MemberInput {
    private String userId;
    private String email;
    private String password;
    private String name;
    private String nickname;
}
