package com.example.community.member.service;

import com.example.community.member.entity.Member;
import com.example.community.member.model.MemberInput;
import com.example.community.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImplement implements MemberService{
    private final MemberRepository memberRepository;
    @Override
    @Transactional
    public boolean register(MemberInput parameter) {
        Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());

        if(optionalMember.isPresent()){
            return false;
        }

        String hashPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());

        Member member = Member.builder()
                .userId(parameter.getUserId())
                .password(hashPassword)
                .email(parameter.getEmail())
                .name(parameter.getName())
                .nickname(parameter.getNickname())
                .createdAt(LocalDateTime.now())
                .build();

        memberRepository.save(member);

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findById(userName);

        if (!optionalMember.isPresent()) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        return new User(member.getUserId(), member.getPassword(), grantedAuthorityList);
    }
}
