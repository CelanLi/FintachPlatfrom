package com.celan.front.view;

import com.celan.commom.enums.RCode;

import java.sql.ResultSet;
import java.util.List;

/*
* 统一的应答结果，controller方法的返回值都是它
* */
public class RespResult {

    private int code;

    private String msg;


    private String accessToken;

    // object data
    private Object data;

    // list data
    private List list;

    // information for paging
    private PageInfo page;

    public static RespResult ok(){
        RespResult result = new RespResult();
        result.setRCode(RCode.SUCCESS);

        return result;
    }

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

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
