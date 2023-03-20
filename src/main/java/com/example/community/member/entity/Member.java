package com.example.community.member.entity;


import com.example.community.member.model.MemberInput;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity (name = "member")
public class Member implements UserDetails {
    @Id
    String username;
    String password;
    String name;
    @Column(unique = true)
    String nickname;
    LocalDateTime createdAt;

    @ElementCollection(fetch = FetchType.EAGER)
    List<String> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static Member of(MemberInput member) {
        String hashPassword = BCrypt.hashpw(member.getPassword(), BCrypt.gensalt());

        return Member.builder()
                .username(member.getUserId())
                .password(hashPassword)
                .name(member.getName())
                .nickname(member.getNickname())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
