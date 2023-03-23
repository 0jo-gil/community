package com.example.community.member.model;


import com.example.community.member.entity.Member;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class MemberDto {
    @Data
    public static class SignIn{
        @NotEmpty(message = "이메일 입력은 필수 입니다.")
        @Email(message = "이메일 형식에 맞지 않습니다.")
        private String username;
        @NotEmpty(message = "비밀번호 입력은 필수입니다.")
        @Size(min = 5, message = "비밀번호는 최소 5자 이상이어야 합니다.")
        private String password;
    }

    @Data
    public static class SignUp{
        @NotEmpty(message = "이메일 입력은 필수 입니다.")
        @Email(message = "이메일 형식에 맞지 않습니다.")
        private String username;

        @NotEmpty(message = "비밀번호 입력은 필수입니다.")
        @Size(min = 5, message = "비밀번호는 최소 5자 이상이어야 합니다.")
        private String password;
        @NotEmpty(message = "이름 입력은 필수입니다.")
        private String name;
        @NotEmpty(message = "닉네임 입력은 필수입니다.")
        @Size(min = 3, message = "닉네임은 최소 3자 이상이어야 합니다.")
        private String nickname;

        private List<String> roles;

        public static Member of(MemberDto.SignUp member) {

            return Member.builder()
                    .username(member.getUsername())
                    .password(member.getPassword())
                    .name(member.getName())
                    .nickname(member.getNickname())
                    .roles(member.getRoles())
                    .createdAt(LocalDateTime.now())
                    .build();
        }
    }


}
