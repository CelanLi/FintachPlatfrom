package com.celan.front.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.celan.commom.enums.RCode;
import com.celan.commom.util.JwtUtil;
import com.celan.front.view.RespResult;
import io.jsonwebtoken.Claims;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.pqc.crypto.falcon.FalconSigner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.PrintWriter;

public class TokenInterceptor implements HandlerInterceptor {
    private String secret = "";

    public TokenInterceptor(String secret){
        this.secret = secret;
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1 if OPTIONS request, return true
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            return true;
        }

        Boolean requestSend = false;

        //2 get token from header and check
        try {
            String headerUid = request.getHeader("uid");
            String headerToken = request.getHeader("Authorization");
            if(StringUtils.isNotBlank(headerToken)){
                // trim the token
                String jwt = headerToken.substring(7);  // remove "Bearer "
                JwtUtil jwtUtil = new JwtUtil(secret);  // use secret key to initialize JwtUtil
                Claims claims = jwtUtil.readJwt(jwt);  // read the token

                // get data from jwt
                Integer uid = claims.get("uid", Integer.class);
                if (headerUid.equals(String.valueOf(uid))){
                    // if header uid and jwt uid are the same, return true
                    requestSend = true;
                }
            }
        } catch (Exception e) {
            requestSend = false;
            e.printStackTrace();
        }
        //token is not valid, give back false
        if (requestSend == false){
            // return json to frontend
            RespResult result = RespResult.fail();
            result.setRCode(RCode.TOKEN_INVALID);

            // use HttpServletResponse give back json
            // cannot convert to json automatically, since it is not controller
            String respJson = JSONObject.toJSONString(result);
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print(respJson);
            writer.flush();
            writer.close();
            return false;
        }
        System.out.println("token is:"+requestSend);
        return requestSend;
    }
}
