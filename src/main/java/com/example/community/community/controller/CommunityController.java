package com.example.community.community.controller;

import com.example.community.community.dto.PostDto;
import com.example.community.community.model.PostingParam;
import com.example.community.community.service.PostingService;
import com.example.community.member.utils.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController{
    private final PostingService postingService;
    private final AuthenticationUtil authenticationUtil;

    @GetMapping("/list")
    @PreAuthorize("hasAnyRole('USER')")
    public String list(
            Model model,
            PostingParam parameter
    ){
        Page<PostDto> postPage = postingService.list(parameter);
        List<PostDto> postingList = postPage.getContent();

        boolean userChecked = authenticationUtil.isAuthenticated();

        String pagerHtml = getPagerHtml(postPage.getTotalElements(), postPage.getSize(), postPage.getNumber(), "");

        model.addAttribute("postingList", postingList);
        model.addAttribute("userChecked", userChecked);
        model.addAttribute("pager", pagerHtml);
        model.addAttribute("listTotalCount", postPage.getTotalElements());

        return "/community/list";
    }

    @GetMapping("/detail")
    public String detail(
            HttpServletRequest request,
            Model model,
            Principal principal
    ) {
        long postNum = Long.parseLong(request.getParameter("id"));

        PostDto posting = postingService.detail(postNum);
        boolean isUserChecked = false;

        boolean userChecked = authenticationUtil.isAuthenticated();

        if(principal != null){
            if(posting.getUserId().equals(principal.getName())){
                isUserChecked = true;
            }
        }

        model.addAttribute("isUserChecked", isUserChecked);
        model.addAttribute("posting", posting);
        model.addAttribute("userChecked", userChecked);


        return "/community/detail";
    }

    @GetMapping("/modify")
    public String modify(
        HttpServletRequest request,
        Model model
    ) {
        long postNum = Long.parseLong(request.getParameter("id"));
        PostDto posting = postingService.detail(postNum);

        model.addAttribute("posting", posting);
        model.addAttribute("type", "modify");

        return "/community/write";
    }

    @GetMapping("/write")
    public String write(
            Model model
    ){
        model.addAttribute("type", "write");
        return "/community/write";
    }
}
