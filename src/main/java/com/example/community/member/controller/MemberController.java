package com.example.community.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @GetMapping("/member/register")
    public String register(){
        return "member/register";
    }

    @GetMapping("/member/login")
    public String login(){
        System.out.println("login");
        return "member/login";
    }
}
