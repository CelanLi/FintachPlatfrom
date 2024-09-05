package com.celan.front.controller;

import com.celan.api.service.*;
import com.celan.front.service.SmsService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BaseController {
//    声明公共的方法和属性

    @Resource
    protected StringRedisTemplate stringRedisTemplate;

    /*平台信息服务*/
    @DubboReference(interfaceClass = PlatBaseInfoService.class, version = "1.0")
    protected PlatBaseInfoService platBaseInfoService;

    /*产品服务*/
    @DubboReference(interfaceClass = ProductService.class, version = "1.0")
    protected ProductService productService;

    /*投资服务*/
    @DubboReference(interfaceClass = InvestService.class, version = "1.0")
    protected InvestService investService;

    /*用户服务*/
    @DubboReference(interfaceClass = UserService.class, version = "1.0")
    protected UserService userService;

    @DubboReference(interfaceClass = RechargeService.class, version = "1.0")
    protected RechargeService rechargeService;


}
