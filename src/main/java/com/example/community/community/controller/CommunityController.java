package com.example.community.community.controller;

import com.example.community.community.dto.ListDto;
import com.example.community.community.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CommunityController {
    private final PostingService postingService;


    @GetMapping("/community/list")
    public String list(
            Model model
    ){
        List<ListDto> postingList = postingService.list();

        if(!postingList.isEmpty()){
            model.addAttribute("postingList", postingList);
        }

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
