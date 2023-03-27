package com.example.community.member.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
       String tokenHeader = request.getHeader("Authorization");

       if(tokenHeader != null && tokenHeader.startsWith("Bearer ")){
           String authToken = tokenHeader.substring(7);
           String username = jwtTokenProvider.getUsername(authToken);

           if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
              Authentication authentication = jwtTokenProvider.getAuthentication(authToken);

              SecurityContextHolder.getContext().setAuthentication(authentication);
           }
       }

        filterChain.doFilter(request, response);
    }
}
