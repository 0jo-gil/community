package com.example.community.member.utils;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class LoginFailureUtil extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof AuthenticationServiceException){
            request.setAttribute("loginFailMsg", "존재하지 않는 사용자입니다.");
        } else if(exception instanceof  BadCredentialsException){
            request.setAttribute("loginFailMsg", "아이디 또는 비밀번호가 틀립니다.");
        } else {
            request.setAttribute("loginFailMsg", "내부 오류가 발생했습니다.");
        }

//        RequestDispatcher dispatcher = request.getRequestDispatcher("/member/login");

//        dispatcher.forward(request, response);


        super.onAuthenticationFailure(request, response, exception);
    }
}
