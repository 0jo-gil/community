package com.example.community.member.utils;

import com.example.community.utils.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private final TokenProvider tokenProvider;
    private final CookieUtil cookieUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
//        String token = resolveTokenFromRequest(request, response);
        Cookie tokenCookie = cookieUtil.getCookie(request, "X-AUTH-TOKEN");
        log.info("tokenCookie ->>>>" + tokenCookie);
        if(tokenCookie != null){
            String token = tokenCookie.getValue();
            log.info("tokennnnnn -> " + token);

            if(StringUtils.hasText(token) && tokenProvider.validateToken(token)){
                Authentication authentication = tokenProvider.getAuthentication(token);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }

//    private String resolveTokenFromRequest (HttpServletRequest request, HttpServletResponse response) throws IOException {
////        String token = request.getHeader(TOKEN_HEADER);
//        try{
//            String token = cookieUtil.getCookie(request, "X-AUTH-TOKEN").getValue();
//            log.info(token);
//
//            if(StringUtils.hasText(token)){
//                return token;
//            }
//        } catch (Exception e){
//            throw new RuntimeException("쿠키가 존재하지 않습니다.");
//        }
////
////        if(!ObjectUtils.isEmpty(token) && token.startsWith(TOKEN_PREFIX)){
////            return token.substring(TOKEN_PREFIX.length());
////        }
//
//
//        return null;
//    }
}
