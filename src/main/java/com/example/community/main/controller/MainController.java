package com.example.community.main.controller;

import com.example.community.member.utils.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
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
