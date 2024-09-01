package com.celan.front.service;

public interface SmsService {

    /**
     * 发送短信验证码
     * @param phone 手机号
     * @return true:发送成功 false:发送失败
     */
    boolean sendSms(String phone) throws Exception;

    /**
     * 校验短信验证码
     * @param phone 手机号
     * @param code 验证码
     * @return true:验证通过 false:验证失败
     */
    boolean checkSmsCode(String phone, String code);
}
