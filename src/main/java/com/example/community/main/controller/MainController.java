package com.example.community.main.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
public class MainController {
    @GetMapping("/")
    public String Main(
            Model model,
            Principal principal
    ) {
        boolean userChecked = false;

        try{
            String userName = principal.getName();
            if(!userName.isEmpty()){
                userChecked = true;
            } else {
                userChecked = false;
            }
        } catch(Exception e){
          userChecked = false;
        }
        model.addAttribute("userChecked", userChecked);

        return "/index";
    }

}
