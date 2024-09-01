package com.celan.commom.enums;

/*
* 枚举值，表示应答码
* */
public enum RCode {

    UNKNOWN(0, "请稍后重试"),
    SUCCESS(1000, "请求成功"),
    PARAM_ERROR(1001, "请求参数有误"),
    PARAM_PRODUCT_TYPE_ERROR(1002, "产品类型参数有误"),
    PRODUCT_OFFLINE(1003, "产品已下线"),
    PHONE_FORMAT_ERROR(1004, "手机号格式有误"),
    PHONE_EXISTS(1005, "手机号已经被注册"),
    SMS_CODE_CAN_USE(1006, "验证码还能继续使用"),
    SMS_CODE_ERROR(1007, "验证码无效"),
    USER_LOGIN_ERROR(1008, "手机号或密码错误"),
    PASSWORD_FORMAT_ERROR(1008, "密码格式有误"),
    REAL_NAME_ERROR(1008, "实名认证信息有误"),
    REAL_NAME_FINISHED(1008, "用户已经完成实名认证"),
    TOKEN_INVALID(1008, "Token无效"),
    SERVER_ERROR(2000, "服务器请求错误"),
    DUBBO_ERROR(3000, "访问dubbo的应答结果"),
    ;

    /**应答码
     * 0：默认值
     * 1000-2000是请求参数有误
     * 2000-3000是服务器请求错误
     * 3000-4000是访问dubbo的应答结果
     */
    private  int code;
    private  String text;

    RCode(int c, String t){
        this.code = c;
        this.text = t;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
