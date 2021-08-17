package com.caihua.roleCenter.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
 * @author XE组-陈正健
 * @version 1.0
 * @date 2021/8/11 21:35
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {


    @Autowired
    private AuthInterceptor authInterceptor;
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/favicon.ico")
//                .addResourceLocations("/resources/favicon.ico");
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**")
        .excludePathPatterns("/resource/**","/favicon.ico")
        .excludePathPatterns("/swagger-ui.html","/swagger-resources/**","/v2/**","/webjars/**")
        .excludePathPatterns("**/*.png");
    }

}
