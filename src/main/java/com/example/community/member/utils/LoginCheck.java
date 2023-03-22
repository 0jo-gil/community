package com.example.community.member.utils;

import com.example.community.member.entity.Member;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@Component
public class LoginCheck implements HandlerInterceptor {
    @Autowired
    private TokenProvider tokenProvider;
    public static final String COOKIE_NAME = "X-AUTH-TOKEN";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = Arrays.stream(
                request.getCookies()
        ).filter(cookie -> cookie.getName().equals(COOKIE_NAME))
                .findFirst().map(Cookie::getValue)
                .orElse("dummy");

//        log.info("token -> " + token);

        try{
            Map<String, Object> info = tokenProvider.parseClaims(token);
            Member member = Member.builder()
                        .username((String) info.get("sub"))
                        .build();

            log.info("member ====? " + info.get("sub"));
            request.setAttribute("login", member);
        } catch (ExpiredJwtException e){
            log.error("토큰이 만료됨");

            ModelAndView mav = new ModelAndView("login");
            mav.addObject("return_url", request.getRequestURI());

            throw new ModelAndViewDefiningException(mav);
        } catch (JwtException ex) {
            log.error("비정상 토큰");

            ModelAndView mav = new ModelAndView("login");

            throw new ModelAndViewDefiningException(mav);
        }

        return true;

    }
}
