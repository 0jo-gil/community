package com.example.community.member.service;

import com.example.community.CommunityApplication;
import com.example.community.member.entity.Member;
import com.example.community.member.exception.MemberExistException;
import com.example.community.member.model.MemberInput;
import com.example.community.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(CommunityApplication.class);
    @Override
    @Transactional
    public boolean register(MemberInput parameter) {

        logger.info("회원가입 시작");

        Member isMember = memberRepository.findById(parameter.getUserId())
                .orElseThrow(() -> {
                    throw new MemberExistException("회원이 이미 존재합니다.");
                });

        String hashPassword = BCrypt.hashpw(parameter.getPassword(), BCrypt.gensalt());

        Member member = Member.builder()
                .userId(parameter.getUserId())
                .password(hashPassword)
                .name(parameter.getName())
                .nickname(parameter.getNickname())
                .createdAt(LocalDateTime.now())
                .build();

        memberRepository.save(member);
        logger.info("회원가입 종료");
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Member> optionalMember = memberRepository.findById(userName);

        if (!optionalMember.isPresent()) {
            logger.debug("회원 정보가 존재하지 않는다.");
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        Member member = optionalMember.get();
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        logger.info(member.getUserId() + " 로그인");
        return new User(member.getUserId(), member.getPassword(), grantedAuthorityList);
    }
}
