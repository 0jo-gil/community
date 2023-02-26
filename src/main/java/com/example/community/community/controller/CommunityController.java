package com.example.community.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommunityController {
    @GetMapping("/community/list")
    public String list(){
        return "/community/list";
    }


    @GetMapping("/community/write")
    public String write(){
        return "/community/write";
    }
    @PostMapping("/community/write")
    public String writeSubmit(){
        return "redirect:/community/list";
    }
}
