package com.example.community.community.controller;

import com.example.community.community.dto.PostDto;
import com.example.community.community.entity.Posting;
import com.example.community.community.model.PostingInput;
import com.example.community.community.model.PostingParam;
import com.example.community.community.service.PostingService;
import com.example.community.member.utils.AuthenticationUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class CommunityController extends BaseController{
    private final PostingService postingService;
    private final AuthenticationUtil authenticationUtil;

    @GetMapping("/community/list")
    @PreAuthorize("hasAnyRole('USER')")
    public String list(
            Model model,
            PostingParam parameter
    ){
        Page<PostDto> postPage = postingService.list(parameter);
        List<PostDto> postingList = postPage.getContent();

        boolean userChecked = authenticationUtil.isAuthenticated();

//        if(!postingList.isEmpty()){
//        }

        String pagerHtml = getPagerHtml(postPage.getTotalElements(), postPage.getSize(), postPage.getNumber(), "");
        model.addAttribute("postingList", postingList);

        model.addAttribute("userChecked", userChecked);
        model.addAttribute("pager", pagerHtml);
        model.addAttribute("listTotalCount", postPage.getTotalElements());

        return "/community/list";
    }

    @GetMapping("/community/detail")
    public String detail(
            HttpServletRequest request,
            Model model,
            Principal principal
    ) {
        long postNum = Long.parseLong(request.getParameter("id"));
        PostDto posting = postingService.detail(postNum);
        boolean checkedUserPost = false;

        boolean userChecked = authenticationUtil.isAuthenticated();

//        if(posting == null){
//            throw new RuntimeException("게시글 상세 정보가 존재하지 않습니다.");
//        }

        if(principal != null){
            if(posting.getUserId().equals(principal.getName())){
                checkedUserPost = true;
            }
        }
        model.addAttribute("checkedUserPost", checkedUserPost);
        model.addAttribute("posting", posting);
        model.addAttribute("userChecked", userChecked);


        return "/community/detail";
    }

    @GetMapping("/community/modify")
    public String modify(
        HttpServletRequest request,
        Model model
    ) {
        long postNum = Long.parseLong(request.getParameter("id"));
        PostDto posting = postingService.detail(postNum);
//
//        if(posting == null){
//            throw new RuntimeException("게시글 상세 정보가 존재하지 않습니다.");
//        }
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
}
