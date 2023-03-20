package com.example.community.member.service;

import com.example.community.CommunityApplication;
import com.example.community.member.entity.Member;
import com.example.community.member.model.MemberInput;
import com.example.community.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
//
//        Optional.ofNullable(
//            memberRepository.findById(parameter.getUserId())
//                    .orElseThrow(() -> new RuntimeException("회원 존재"))
//            );

        memberRepository.save(Member.of(parameter));
        logger.info("회원가입 종료");

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Member> optionalMember =
                Optional.ofNullable(
                    memberRepository.findById(userName)
                        .orElseThrow(() -> {
                            logger.debug("회원 정보가 존재하지 않는다.");
                            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
                        }));

        Member member = optionalMember.get();
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        logger.info(member.getUsername() + " 로그인");
        return new User(member.getUsername(), member.getPassword(), grantedAuthorityList);
    }
}
