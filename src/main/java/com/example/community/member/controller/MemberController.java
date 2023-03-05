package com.example.community.member.controller;

import com.example.community.member.model.MemberInput;
import com.example.community.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/member/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "exception", required = false) String exception,
            Model model
    ){

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "member/login";
    }

    @PostMapping("/member/login")
    public String loginSubmit(){
        return "member/login";
    }

    @GetMapping("/member/register")
    public String register(){
        return "/member/register";
    }
    @PostMapping("/member/register")
    public String registerSubmit(
            @Validated MemberInput parameter,
            Model model
    ){
        boolean result = memberService.register(parameter);

        if(!result) {
            model.addAttribute("result", result);
        }

        return "member/register-complete";
    }
}
