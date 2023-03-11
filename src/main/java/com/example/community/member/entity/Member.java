package com.example.community.member.entity;


import com.example.community.member.model.MemberInput;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity (name = "member")
public class Member {
    @Id
    String userId;
    String password;
    String name;
    @Column(unique = true)
    String nickname;
    LocalDateTime createdAt;

    public static Member of(MemberInput member) {
        String hashPassword = BCrypt.hashpw(member.getPassword(), BCrypt.gensalt());

        return Member.builder()
                .userId(member.getUserId())
                .password(hashPassword)
                .name(member.getName())
                .nickname(member.getNickname())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
