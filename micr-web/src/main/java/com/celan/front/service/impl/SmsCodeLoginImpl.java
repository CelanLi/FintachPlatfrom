package com.celan.front.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.aliyuncs.dysmsapi.model.v20170525.*;

import com.celan.commom.constants.RedisKey;
import com.celan.front.config.ALiYunConfig;
import com.celan.front.service.SmsService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service(value = "smsCodeLoginImpl")
public class SmsCodeLoginImpl implements SmsService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ALiYunConfig ALiYunConfig;

    @Override
    public boolean sendSms(String phone) throws Exception {
        boolean send = false;
        // 设置短信内容
        String random = RandomStringUtils.randomNumeric(6);
        System.out.println("发送验证码："+random);

        String accessKeyId = ALiYunConfig.getAccessKeyId();
        String accessKeySecret = ALiYunConfig.getAccessKeySecret();

        String key = RedisKey.KEY_SMS_CODE_LOGIN + phone;
        stringRedisTemplate.opsForValue().set(key, random, 15, TimeUnit.MINUTES);
        send = true;
//
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
//
//        IAcsClient client = new DefaultAcsClient(profile);


//        SendSmsRequest request = new SendSmsRequest();
//        request.setSignName(ALiYunConfig.getSignName());
//        request.setTemplateCode(ALiYunConfig.getTemplateCode());
//        request.setPhoneNumbers(phone);
//        String jsonParam = "{\"code\":\"" + random + "\"}";
//        request.setTemplateParam(jsonParam);
//
//        try {
//            SendSmsResponse response = client.getAcsResponse(request);
//            System.out.println(new Gson().toJson(response));
//            if (response.getCode().equals("OK")){
//                String key = RedisKey.KEY_SMS_CODE_LOGIN + phone;
//                stringRedisTemplate.opsForValue().set(key, random, 15, TimeUnit.MINUTES);
//                send = true;
//            }
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            System.out.println("ErrCode:" + e.getErrCode());
//            System.out.println("ErrMsg:" + e.getErrMsg());
//            System.out.println("RequestId:" + e.getRequestId());
//        }

        return send;
    }

    /**
     * 验证短信验证码
     * @param phone 手机号
     * @param code 验证码
     * @return
     */
    @Override
    public boolean checkSmsCode(String phone, String code) {
        String key = RedisKey.KEY_SMS_CODE_LOGIN + phone;
        if (stringRedisTemplate.hasKey(key)){
            String redisCode = stringRedisTemplate.boundValueOps(key).get();
            if (code.equals(redisCode)){
                return true;
            }
        }
        return false;
    }
}
