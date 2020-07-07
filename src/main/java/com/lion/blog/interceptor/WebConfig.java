package com.lion.blog.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns("/","/login","/doLogin","/regist","/doregist","/checkLogin","/css/*","/js/*","/fonts/*","/images/*","/articles/*","/user/*");
    }
}
