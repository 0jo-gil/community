package com.example.community.community.controller;

import com.example.community.community.dto.ListDto;
import com.example.community.community.entity.Posting;
import com.example.community.community.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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

    @GetMapping("/community/detail")
    public String detail(
            HttpServletRequest request,
            Model model,
            Principal principal
    ) {
        long postNum = Long.parseLong(request.getParameter("id"));
        Posting posting = postingService.detail(postNum);
        boolean checkedUserPost = false;

        if(posting == null){
            throw new RuntimeException("게시글 상세 정보가 존재하지 않습니다.");
        }


        if(principal != null){
            if(posting.getUserId().equals(principal.getName())){
                checkedUserPost = true;
            }
        }


        model.addAttribute("checkedUserPost", checkedUserPost);
        model.addAttribute("posting", posting);


        return "/community/detail";
    }

    @GetMapping("/community/modify")
    public String modify(
        HttpServletRequest request,
        Model model
    ) {
        long postNum = Long.parseLong(request.getParameter("id"));
        Posting posting = postingService.detail(postNum);

        if(posting == null){
            throw new RuntimeException("게시글 상세 정보가 존재하지 않습니다.");
        }
        model.addAttribute("posting", posting);
        model.addAttribute("type", "modify");

        return "/community/write";
    }



    @GetMapping("/community/write")
    public String write(
            Model model
    ){
        model.addAttribute("type", "write");
        return "/community/write";
    }
    @PostMapping("/community/write")
    public String writeSubmit(){
        return "redirect:/community/list";
    }
}
