package com.celan.commom.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

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

    /**
     * check mobile
     * @param mobile
     * @return true if mobile is valid
     */
    public static boolean checkMobile(String mobile) {
        boolean flag = false;
        if (mobile != null && mobile.length() == 11) {
            flag = Pattern.matches("^1[1-9]\\d{9}$", mobile);
        }
        return flag;
    }

    /*compare bigdecimal n1>=n2 true*/
    public static boolean ge(BigDecimal n1, BigDecimal n2) {
        if (n1 == null || n2 == null) {
            throw new RuntimeException("BigDecimal is null");
        } else {
            return n1.compareTo(n2) >= 0;
        }
    }
}
