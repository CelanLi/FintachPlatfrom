package com.celan.dataservice;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// start dubbo
@EnableDubbo
// mapper scan
@MapperScan(basePackages = "com.celan.dataservice.mapper")
@SpringBootApplication
public class MicrDataserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicrDataserviceApplication.class, args);
    }

}
