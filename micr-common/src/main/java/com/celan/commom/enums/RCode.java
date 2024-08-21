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
