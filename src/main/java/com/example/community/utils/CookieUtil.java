package com.example.community.utils;

import com.example.community.member.utils.TokenProvider;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class CookieUtil {
    public Cookie createCookie(String cookieName, String value){
        Cookie cookie = new Cookie(cookieName, value);

        cookie.setSecure(true);
        cookie.setMaxAge((int) TokenProvider.TOKEN_EXPIRE_TIME);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    public Cookie getCookie(
            HttpServletRequest request,
            String cookieName
    ) {
        try{
            Cookie[] cookies = request.getCookies();

            if(cookies == null) {
                return null;
            }

            for(Cookie cookie : cookies) {
                if(cookie.getName().equals(cookieName)){
                    return cookie;
                }
            }

        } catch(RuntimeException e){
            throw new RuntimeException("쿠키 에러");
        }


        return null;
    }
}
