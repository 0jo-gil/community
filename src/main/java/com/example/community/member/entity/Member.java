package com.example.community.member.entity;


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
public class Member {
    @Id
    String userId;
    String email;
    String password;
    String name;
    String nickname;
    LocalDateTime createdAt;
}
