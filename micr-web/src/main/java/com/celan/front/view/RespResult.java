package com.celan.front.view;

import com.celan.commom.enums.RCode;

import java.sql.ResultSet;
import java.util.List;

/*
* 统一的应答结果，controller方法的返回值都是它
* */
public class RespResult {

    //应答码，自定义的数字
    private int code;

    //应答消息，一般是错误消息，给用户看
    private String msg;

    //应答数据，一般是查询结果
    private Object data;

    //应答数据，存放集合数据
    private List list;

    //分页数据
    private PageInfo page;

    //表示成功的response result对象
    public static RespResult ok(){
        RespResult result = new RespResult();
        result.setRCode(RCode.SUCCESS);

        return result;
    }

    //表示失败的response result对象
    public static RespResult fail(){
        RespResult result = new RespResult();
        result.setRCode(RCode.UNKNOWN);

        return result;
    }

    public void setRCode(RCode rCode){
        this.code = rCode.getCode();
        this.msg = rCode.getText();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public PageInfo getPage() {
        return page;
    }

    public void setPage(PageInfo page) {
        this.page = page;
    }
}
