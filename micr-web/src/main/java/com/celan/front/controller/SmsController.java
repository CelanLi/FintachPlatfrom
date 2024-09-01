package com.celan.front.controller;

import com.celan.commom.constants.RedisKey;
import com.celan.commom.enums.RCode;
import com.celan.commom.util.CommonUtil;
import com.celan.front.service.SmsService;
import com.celan.front.view.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "短信接口")
@RequestMapping("/v1/sms")
public class SmsController extends BaseController{

    @Resource(name = "smsCodeRegisterImpl")
    private SmsService smsService;

    @Resource(name = "smsCodeLoginImpl")
    private SmsService loginSmsService;

    @Resource(name = "smsCodeRealNameImpl")
    private SmsService realNameSmsService;


    @GetMapping("/code/register")
    @ApiOperation(value = "发送注册验证码", notes = "发送注册验证码")
    public RespResult sendCodeRegister(@RequestParam("phone") String phone) throws Exception {
        RespResult result = RespResult.fail();
        if (CommonUtil.checkMobile(phone)) {
            String key = RedisKey.KEY_SMS_CODE_REG + phone;
            if (stringRedisTemplate.hasKey(key)) {
                result = RespResult.ok();
                result.setRCode(RCode.SMS_CODE_CAN_USE);}
            else {
                boolean isSuccess = smsService.sendSms(phone);
                if (isSuccess) {
                    result = RespResult.ok();
                }
            }
        } else {
            result.setRCode(RCode.PHONE_FORMAT_ERROR);
        }
        return result;
    }

    @GetMapping("/code/login")
    @ApiOperation(value = "发送登录验证码", notes = "发送登录验证码")
    public RespResult sendCodeLogin(@RequestParam("phone") String phone) throws Exception {
        RespResult result = RespResult.fail();
        if (CommonUtil.checkMobile(phone)) {
            // check redis for phone number
            String key = RedisKey.KEY_SMS_CODE_LOGIN + phone;
            if (stringRedisTemplate.hasKey(key)) {   // if phone exists
                result = RespResult.ok();
                result.setRCode(RCode.SMS_CODE_CAN_USE);
            } else {
                boolean isSuccess = loginSmsService.sendSms(phone);
                if (isSuccess) {
                    result = RespResult.ok();
                }
            }
        } else {
            result.setRCode(RCode.PHONE_FORMAT_ERROR);
        }
        return result;
    }

    @GetMapping("/code/realname")
    @ApiOperation(value = "发送实名认证验证码", notes = "发送实名认证验证码")
    public RespResult sendCodeRealName(@RequestParam("phone") String phone) throws Exception {
        RespResult result = RespResult.fail();
        if (CommonUtil.checkMobile(phone)) {
            // check redis for phone number
            String key = RedisKey.KEY_SMS_CODE_REALNAME + phone;
            if (stringRedisTemplate.hasKey(key)) {   // if phone exists
                result = RespResult.ok();
                result.setRCode(RCode.SMS_CODE_CAN_USE);
            } else {
                boolean isSuccess = realNameSmsService.sendSms(phone);
                if (isSuccess) {
                    result = RespResult.ok();
                }
            }
        } else {
            result.setRCode(RCode.PHONE_FORMAT_ERROR);
        }
        return result;
    }
}
