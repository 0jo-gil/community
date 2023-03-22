//package com.example.community.member.utils;
//
//import com.example.community.member.entity.Member;
//import com.example.community.member.model.LoginUser;
//import com.example.community.utils.CookieUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.MethodParameter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class LoginUserResolver implements HandlerMethodArgumentResolver {
//    @Autowired
//    private TokenProvider tokenProvider;
//
//    @Autowired
//    private CookieUtil cookieUtil;
//
//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        return parameter.hasParameterAnnotation(LoginUser.class);
//    }
//
//    @Override
//    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//        final Map<String, Object> resolved = new HashMap<>();
//
//        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
//
//        String token = cookieUtil.getCookie(request, LoginCheck.COOKIE_NAME).getValue();
//        Map<String, Object> info = tokenProvider.verifyToken(token);
//
//        if(parameter.getParameterType().isAssignableFrom(String.class)){
//            resolved.put("resolved", info.get(parameter.getParameterName()));
//        }
//        else if(parameter.getParameterType().isAssignableFrom(Member.class)){
//            Member member = Member.builder()
//                    .username((String) info.get("sub"))
//                    .build();
//            resolved.put("resolved", member);
//        }
//
//        return resolved.get("resolved");
//    }
//}
