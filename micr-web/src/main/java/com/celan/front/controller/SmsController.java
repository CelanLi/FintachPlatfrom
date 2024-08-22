package com.celan.front.controller;

import com.celan.front.view.RespResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "短信接口")
@RequestMapping("/v1/sms")
public class SmsController extends BaseController{

    /*发送注册验证码接口*/
    @GetMapping("/code/register")
    public RespResult sendCodeRegister(@RequestParam("phone") String phone){
        RespResult result = RespResult.fail();
        return result;
    }
}
