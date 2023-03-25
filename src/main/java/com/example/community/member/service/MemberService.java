package com.example.community.member.service;

import com.example.community.member.entity.Member;
import com.example.community.member.model.MemberDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.Cookie;


public interface MemberService extends UserDetailsService {
    Member register(MemberDto.SignUp member);
    Member authenticate(MemberDto.SignIn member);
    boolean logout();
}
