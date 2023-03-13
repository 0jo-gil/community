package com.example.community.member.controller;

import com.example.community.member.model.MemberInput;
import com.example.community.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

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
            @Valid MemberInput parameter,
            BindingResult bindingResult,
            Model model
    ){
        boolean result = memberService.register(parameter);

        HashMap<String, String> validMap = new HashMap<>();

        if(bindingResult.hasErrors()){
            List<ObjectError> errorList =
                    bindingResult.getAllErrors();

            for(ObjectError error : errorList){
                if(error instanceof FieldError){
                    FieldError fieldError = (FieldError) error;
                    validMap.put(fieldError.getField(), error.getDefaultMessage());
//                    System.out.println(fieldError.getField() + ": " + error.getDefaultMessage());
                }
            }

            model.addAttribute("validMap", validMap);
            return "member/register";
        }

        return "member/register-complete";
    }
}
