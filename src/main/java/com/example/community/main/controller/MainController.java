package com.example.community.main.controller;

import com.example.community.member.entity.Member;
import com.example.community.member.utils.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final AuthenticationUtil authenticationUtil;
    @GetMapping("/")
    public String Main(
            Model model
    ) {
        boolean userChecked = authenticationUtil.isAuthenticated();
        model.addAttribute("userChecked", userChecked);
        return "/index";
    }

}
