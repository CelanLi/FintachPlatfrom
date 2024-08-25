package com.celan.front.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.celan.commom.constants.RedisKey;
import com.celan.front.config.JdwxSmsConfig;
import com.celan.front.service.SmsService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class SmsCodeRegisterImpl implements SmsService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    // 注入短信配置
    @Resource
    private JdwxSmsConfig smsConfig;
    @Override
    public boolean sendSms(String phone) {
        boolean send = true;
        // 设置短信内容
        String random = RandomStringUtils.randomNumeric(6);
        System.out.println("发送短信验证码："+random);

        // 替换短信内容中的%s
        String content = String.format(smsConfig.getContent(),random);

        // 把短信验证码存到redis
        String key = RedisKey.KEY_SMS_CODE_REG + phone;
        // 三分钟后验证码过期
        stringRedisTemplate.boundValueOps(key).set(random, 3, TimeUnit.MINUTES);

        // 使用http请求发送短信
        CloseableHttpClient client = HttpClients.createDefault();
        // 确保url匹配
        String url = smsConfig.getUrl() + "?mobile=" + phone
                + "&content=" + content
                + "&appkey=" + smsConfig.getAppkey();

//        HttpGet get = new HttpGet(url);

//        try {
//            CloseableHttpResponse response = client.execute(get);
//            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
//                // 得到返回的数据，json格式
//                String text = EntityUtils.toString(response.getEntity());
//
//                if (StringUtils.isNotBlank(text)){
//                    // 解析json
//                    JSONObject jsonObject = JSONObject.parseObject(text);
//                    // 判断是否发送成功
//                    if ("0".equals(jsonObject.getString("code"))){  // 接口调用成功
//                        if ("success".equalsIgnoreCase(jsonObject.getJSONObject("result").getString("responseMessage"))){
//                            // 发送成功
//                            send = true;
//
//                            // 把短信验证码存到redis
//                            String key = RedisKey.KEY_SMS_CODE_REG + phone;
//                            // 三分钟后验证码过期
//                            stringRedisTemplate.boundValueOps(key).set(random, 3, TimeUnit.MINUTES);
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
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
        String key = RedisKey.KEY_SMS_CODE_REG + phone;
        if (stringRedisTemplate.hasKey(key)){
            String redisCode = stringRedisTemplate.boundValueOps(key).get();
            if (code.equals(redisCode)){
                return true;
            }
        }
        return false;
    }
}
