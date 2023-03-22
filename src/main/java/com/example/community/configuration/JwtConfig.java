//package com.example.community.configuration;
//
//import com.example.community.member.utils.LoginCheck;
//import com.example.community.member.utils.LoginUserResolver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
//@Configuration
//public class JwtConfig implements WebMvcConfigurer {
//    @Autowired
//    private LoginUserResolver loginUserResolver;
//    @Autowired
//    LoginCheck loginCheck;
//
////    @Override
////    public void addInterceptors(InterceptorRegistry registry) {
////        registry
////                .addInterceptor(loginCheck)
////                .addPathPatterns("", "/**")
////                .excludePathPatterns(
////                        "/",
////                        "/**/login",
////                        "/**/register",
////                        "/resources/**",
////                        "/css/**"
////                );
////    }
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//        resolvers.add(loginUserResolver);
//    }
//}
