package com.example.community.member.service;

import com.example.community.CommunityApplication;
import com.example.community.member.entity.Member;
import com.example.community.member.entity.MemberRoleCode;
import com.example.community.member.model.MemberInput;
import com.example.community.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MemberServiceImplement implements MemberService{
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final Logger logger = LoggerFactory.getLogger(CommunityApplication.class);

    @Override
//    @Transactional
    public Member register(MemberInput.SignUp parameter) {
        logger.info("회원가입 시작");

        boolean exists = memberRepository.existsByUsername(parameter.getUsername());


        if(exists){
//            throw new AlreadyBoundException();
            throw new RuntimeException("아이디가 존재합니다.");
        }

        List<String> role = new ArrayList<>();
        role.add(MemberRoleCode.ROLE_USER.name());

        parameter.setPassword(passwordEncoder.encode(parameter.getPassword()));
        parameter.setRoles(role);

        Member result = memberRepository.save(MemberInput.SignUp.of(parameter));

        logger.info("회원가입 종료");
        return result;
    }

    @Override
    public Member authenticate(MemberInput.SignIn member) {
        Member user = memberRepository.findByUsername(member.getUsername())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 ID입니다."));

        if(!passwordEncoder.matches(member.getPassword(), user.getPassword())){
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("couldn't find user -> " + username));
        return member;
    }
}
