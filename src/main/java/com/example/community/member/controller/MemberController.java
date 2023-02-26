package com.example.community.member.controller;

import com.example.community.member.model.MemberInput;
import com.example.community.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    @RequestMapping(value = "/member/login", method = RequestMethod.GET)
    public String login(){
        return "member/login";
    }

    @PostMapping("/member/login")
    public String loginSubmit(){
        return "redirect:/";
    }

    @GetMapping("/member/register")
    public String register(){
        return "/member/register";
    }
    @PostMapping("/member/register")
    public String registerSubmit(
            MemberInput parameter
    ){
        boolean result = memberService.register(parameter);

        return "redirect:/";
    }



}
