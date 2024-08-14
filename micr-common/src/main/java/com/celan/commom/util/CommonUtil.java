package com.celan.commom.util;

public class CommonUtil {

    /*处理page number*/
    public static int defaultPageNo(Integer pageNo) {
        int pNo = pageNo;
        if (pageNo == null || pageNo < 1) {
            pNo = 1;
        }
        return pNo;
    }

    /*处理pagesize*/
    public static int defaultPageSize(Integer pageSize) {
        int pSize = pageSize;
        if (pageSize == null || pageSize < 1) {
            pSize = 1;
        }
        return pSize;
    }

    /*手机号脱敏*/
    public static String maskMobile(String mobile) {
        String result = "***********";
        if (mobile != null && mobile.length() == 11) {
            result = mobile.substring(0, 3) + "******" + mobile.substring(9,11);
        }
        return result;
    }
}
