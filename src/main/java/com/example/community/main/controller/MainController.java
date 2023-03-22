package com.example.community.main.controller;

import com.example.community.member.entity.Member;
import com.example.community.member.model.LoginUser;
import com.example.community.member.utils.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainController {
    private final AuthenticationUtil authenticationUtil;
    @GetMapping("/")
    public String Main(
            Model model,
            @LoginUser String id
    ) {
        boolean userChecked = authenticationUtil.isAuthenticated();
        log.info("user -> " + id);

        model.addAttribute("userChecked", userChecked);
        return "/index";
    }

}
