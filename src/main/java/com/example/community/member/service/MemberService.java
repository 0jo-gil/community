package com.example.community.member.service;

import com.example.community.member.entity.Member;
import com.example.community.member.model.MemberInput;
import com.example.community.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface MemberService extends UserDetailsService {
    Member register(MemberInput.SignUp member);

    Member authenticate(MemberInput.SignIn member);
}
