package com.itheima.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截全部路径，跨域放到最上面
         */
        registry.addInterceptor(new CorsInterceptor()).addPathPatterns("/**");
        //拦截哪些路径


        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

