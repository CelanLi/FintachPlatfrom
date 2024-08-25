package com.celan.service;

public interface SmsService {

    /**
     * 发送短信验证码
     * @param phone 手机号
     * @param content 短信内容
     * @return
     */
    boolean sendSms(String phone, String content);
}
