package com.example.community.member.controller;

import com.example.community.member.entity.Member;
import com.example.community.member.model.MemberInput;
import com.example.community.member.service.MemberService;
import com.example.community.member.utils.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody MemberInput.SignIn request
    ){
        Member member = memberService.authenticate(request);
        String token = tokenProvider.generateToken(member.getUsername(), member.getRoles());

        return ResponseEntity.ok(token);
    }
}
