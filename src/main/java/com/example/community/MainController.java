package com.example.community;

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
            Model model
    ) {
        boolean userChecked = false;

        try{


        } catch(Exception e){
            model.addAttribute("userChecked", userChecked);
        }


        return "/index";
    }

}
