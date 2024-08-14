package com.celan.commom.util;

public class CommonUtil {

    /*处理page number*/
    public static int defaultPageNo(Integer pageNo) {
        int pNo = pageNo;
        /*当pageNo不正确的时候，返回1*/
        if (pageNo == null || pageNo < 1) {
            pNo = 1;
        }
        return pNo;
    }

    /*处理pagesize*/
    public static int defaultPageSize(Integer pageSize) {
        int pSize = pageSize;
        /*当pageSize不正确的时候，返回1*/
        if (pageSize == null || pageSize < 1) {
            pSize = 1;
        }
        return pSize;
    }
}
