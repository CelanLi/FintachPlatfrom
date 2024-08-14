package com.celan.front.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurationSettings {

    @Bean
    public Docket docket(){
        //1创建Docket对象
        Docket docket = new Docket(DocumentationType.SWAGGER_2);

        //2创建API信息，接口文档的总体描述
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("金融项目")
                .version("1.0")
                .description("前后端分离项目，前端Vue，后端SpringBoot+Dubbo分布式项目")
                .contact(new Contact("celanLi", "", "celan.li2000@gmail.com"))
                .license("Apache 2.0")
                .build();

        //3设置API信息
        docket = docket.apiInfo(apiInfo);

        //4设置参与文档生成的包
        docket = docket.select().apis(RequestHandlerSelectors.basePackage("com.celan.front.controller")).build();

        return docket;
    }
}
