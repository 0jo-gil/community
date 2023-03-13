package com.example.community.member.utils;

import com.example.community.member.entity.Member;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtil {
    public boolean isAuthenticated(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())){
            return false;
        }

        return authentication.isAuthenticated();
    }

    public String isLoginUserId(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())){
            return null;
        }
        return authentication.getName();
    }
}
