package com.celan.front.service.impl;

import com.celan.front.config.ALiYunConfig;
import com.celan.front.service.RealNameService;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class RealNameServiceImpl implements RealNameService {
    @Resource
    private ALiYunConfig aLiYunConfig;

    @Override
    public boolean realNameCheck(String name, String idCard) {
        boolean isRealName = false;
        String url = "https://idenauthen.market.alicloudapi.com/idenAuthentication";
        // 获取appCode链接：https://market.console.aliyun.com/
        String appCode = aLiYunConfig.getAppCode();

        try {
            String result = postData(appCode, url, name, idCard);
            if (result.contains("身份证信息匹配"))
                isRealName = true;
            System.out.println("result:" + result);
            // store the user real name and id card to database
        } catch (Exception e) {
            System.out.println("realNameCheck failed, message:" + e.getMessage());
        }

        return isRealName;
    }

    public static String postData(String appCode, String url, String name, String idNo) throws IOException {
        String result = "";
        RequestBody formBody = new FormBody.Builder().add("name", name).add("idNo", idNo).build();
        Request request = new Request.Builder().url(url).addHeader("Authorization", "APPCODE " + appCode).post(formBody).build();

        Call call = new OkHttpClient().newCall(request);
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            System.out.println("execute failed, message:" + e.getMessage());
        }

        assert response != null;
        if (!response.isSuccessful()) {      // 当返回结果发生错误时
            // 状态码为403时一般是套餐包用尽，需续购；注意：续购不会改变秘钥（appCode），仅增加次数
            // 续购链接：https://market.aliyun.com/products/57000002/cmapi025518.html
            System.out.println("request failed----" + "返回状态码" + response.code()  + ",message:" + response.message());
        }
        result = response.body().string();    //此处不可以使用toString()方法，该方法已过期

        return result;
    }

}
