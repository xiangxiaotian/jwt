package com.xxt.token.configuration;

import com.xxt.token.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
//@EnableWebMvc使用@EnableWebMvc，也代表我们放弃了 spring 提供的默认配置会导致配置文件的mvc无效
public class MvcConfiguration implements WebMvcConfigurer{
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathPatterns = new ArrayList<>();
        excludePathPatterns.add("/index/sign");
        excludePathPatterns.add("/index/login");
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns(excludePathPatterns);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public HandlerInterceptor tokenInterceptor(){
        return new TokenInterceptor();
    }
}
