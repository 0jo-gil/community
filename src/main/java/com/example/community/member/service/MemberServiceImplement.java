package com.example.community.member.service;

import com.example.community.CommunityApplication;
import com.example.community.member.entity.Member;
import com.example.community.member.model.MemberInput;
import com.example.community.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

//        if(exists){
//            throw new AlreadyBoundException();
//        }

        parameter.setPassword(passwordEncoder.encode(parameter.getPassword()));

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
        return  memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("couldn't find user -> " + username));
//        Optional<Member> optionalMember =
//                Optional.ofNullable(
//                    memberRepository.findById(username)
//                        .orElseThrow(() -> {
//                            logger.debug("회원 정보가 존재하지 않는다.");
//                            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
//                        }));
//
//        Member member = optionalMember.get();
//        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
//
//        logger.info(member.getUsername() + " 로그인");
//        return new User(member.getUsername(), member.getPassword(), grantedAuthorityList);
    }
}
