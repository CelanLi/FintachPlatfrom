package com.celan.front;

import com.celan.commom.util.JwtUtil;
import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// 启动swagger和ui
@EnableSwagger2
@EnableSwaggerBootstrapUI

// 启动dubbo
@EnableDubbo
@SpringBootApplication
public class MicrWebApplication {

    @Value("${jwt.secret-key}")
    private String secretKey;

    // create jwtUtil bean
    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil(secretKey);
    }

    public static void main(String[] args) {
        SpringApplication.run(MicrWebApplication.class, args);
    }

}
