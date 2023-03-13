package com.example.community.community.utils;

import com.example.community.community.repository.PostingRepository;
import com.example.community.utils.SessionsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    PostingRepository postingRepository;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String httpMethod = request.getMethod();
        // interceptors 테스트
//        if(httpMethod.equals("POST") || httpMethod.equals("DELETE")) {
//        if(httpMethod.equals("GET")) {
//            String sessionItem = (String) request.getAttribute(SessionsUtil.SESSION_ID);
//
//            System.out.println(sessionItem);
//
//            if(sessionItem == null){
//                response.getOutputStream().println("LOGIN REQUIRED");
//                return false;
//            }
//
//            return true;
//        }

        if(httpMethod.equals("POST") || httpMethod.equals("DELETE")) {
            String sessionItem = (String) request.getSession().getAttribute(SessionsUtil.SESSION_ID);
            Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

            System.out.println(sessionItem);

//            long id = Long.parseLong((String) pathVariables.get("id"));
//
//            Posting posting = postingRepository.findById(id).get();
//
//            System.out.println(posting);

        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
