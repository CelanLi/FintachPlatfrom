package com.celan.front.settings;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 要处理的请求地址
                .allowedOrigins("http://localhost:8081", "http://localhost:8080") // 允许所有域名访问 (http://localhost:8080)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD") // 允许所有方法
                .allowCredentials(true) // 允许携带cookie
                .maxAge(3600) // 1小时内不需要再预检（发OPTIONS请求）
                .allowedHeaders("*"); // 允许所有请求头
    }
}
