package com.example.community.member.entity;


import lombok.*;

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
}
