package com.example.community.member.controller;

import com.example.community.member.entity.Member;
import com.example.community.member.model.MemberInput;
import com.example.community.member.service.MemberService;
import com.example.community.member.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class ApiMemberController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody MemberInput.SignUp request
    ){
        Member result = memberService.register(request);

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(
                URI.create("/member/register-complete")
        );

//        return ResponseEntity.ok(result);
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody MemberInput.SignIn request,
            HttpServletResponse response
    ){
        Member member = memberService.authenticate(request);
        String token = tokenProvider.generateToken(member.getUsername(), member.getRoles());
        System.out.println(token);

        response.setHeader("X-AUTH-TOKEN", token);
        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);

        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }
}
