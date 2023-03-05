package com.example.community.member.controller;

import com.example.community.member.model.MemberInput;
import com.example.community.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    @RequestMapping(value = "/member/login", method = RequestMethod.GET)
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
    public String loginSubmit(

    ){
        return "member/login";
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
