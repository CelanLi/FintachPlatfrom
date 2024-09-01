package com.celan.front.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ALiYunConfig {
    @Value("${aliyun.sms.access-key-id}")
    private String accessKeyId;
    @Value("${aliyun.sms.access-key-secret}")
    private String accessKeySecret;

    @Value("${aliyun.sms.sign-name}")
    private String SignName;

    @Value("${aliyun.sms.template-code}")
    private String TemplateCode;

    @Value("${aliyun.real-name.app-code}")
    private String appCode;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public String getSignName() {
        return SignName;
    }

    public String getTemplateCode() {
        return TemplateCode;
    }

    public String getAppCode() {
        return appCode;
    }
}
