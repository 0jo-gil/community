package com.example.community.member.controller;

import com.example.community.member.entity.Member;
import com.example.community.member.model.MemberDto;
import com.example.community.member.service.MemberService;
import com.example.community.member.utils.JwtTokenProvider;
import com.example.community.utils.CookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class ApiMemberController {
    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody MemberDto.SignUp request
    ){
        Member result = memberService.register(request);
        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(
                URI.create("/member/register-complete")
        );

        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody MemberDto.SignIn request,
            HttpServletResponse response
    ){
        System.out.println("로그인");

        String token = memberService.authenticate(request);


        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);


//        Cookie cookie = cookieUtil.createCookie("X-AUTH-TOKEN"
//                , token);
//
//        response.addCookie(cookie);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(map);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            HttpServletResponse response
    ){
        boolean logoutResult = memberService.logout();

//        Cookie cookie = cookieUtil.createCookie("X-AUTH-TOKEN", null);
//        response.addCookie(cookie);

        return ResponseEntity.ok(logoutResult);
    }
}
