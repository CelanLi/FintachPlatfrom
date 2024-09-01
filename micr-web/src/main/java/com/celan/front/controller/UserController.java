package com.celan.front.controller;

import com.celan.api.model.User;
import com.celan.commom.enums.RCode;
import com.celan.commom.util.CommonUtil;
import com.celan.commom.util.JwtUtil;
import com.celan.front.service.RealNameService;
import com.celan.front.service.SmsService;
import com.celan.front.service.impl.SmsCodeLoginImpl;
import com.celan.front.view.RespResult;
import com.celan.front.vo.RealnameVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/user")
@Api(tags = "用户接口")
public class UserController extends BaseController{
    @Resource(name = "smsCodeRegisterImpl")
    private SmsService smsService;

    @Resource(name = "smsCodeLoginImpl")
    private SmsService smsCodeLoginImpl;

    @Resource(name = "smsCodeRealNameImpl")
    private SmsService smsCodeRealNameImpl;

    @Resource
    private RealNameService realNameService;

    @Resource
    private JwtUtil jwtUtil;

    /*register user using phone number*/
    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public RespResult userRegister(@RequestParam("phone") String phone,
                                    @RequestParam("pword") String pword,
                                    @RequestParam("scode") String scode){
        RespResult result = RespResult.fail();

        // 1. check parameters
        if (CommonUtil.checkMobile(phone)){
            // check password length
            if (pword != null && pword.length() == 32){
                // check sms code
                if (smsService.checkSmsCode(phone, scode)){
                    // register logic
                    int registerResult = userService.userRegister(phone, pword);
                    if (registerResult == 1){
                        result = RespResult.ok();
                    } else if (registerResult == 2) {
                        result.setRCode(RCode.PHONE_EXISTS);
                    } else {
                        result.setRCode(RCode.PARAM_ERROR);
                    }
                } else {
                    result.setRCode(RCode.SMS_CODE_ERROR);
                }
            } else {
                result.setRCode(RCode.PASSWORD_FORMAT_ERROR);
            }
        } else{
            result.setRCode(RCode.PHONE_FORMAT_ERROR);
        }


        return result;
    }

    /*check phone number*/
    @ApiOperation(value = "判断手机号是否存在", notes = "检查手机号是否存在")
    @GetMapping("/phone/exists")
    public RespResult phoneExists(@RequestParam("phone") String phone) {
        RespResult result = new RespResult();
        result.setRCode(RCode.PHONE_EXISTS);
        // 1. validate phone number
        if (CommonUtil.checkMobile(phone)){
            // 2. check if phone number exists
            User user = userService.queryByPhone(phone);
            if (user == null) {
                result = RespResult.ok();
            }
            // put the phone number into redis, then in the future we can first check redis to see if the phone number exists
        }else {
            result.setRCode(RCode.PHONE_FORMAT_ERROR);
        }

        return result;
    }

    @ApiOperation(value = "用户登录", notes = "用户登录，获取访问token")
    @PostMapping("/login")
    public RespResult userLogin(@RequestParam String phone,
                                @RequestParam String pword,
                                @RequestParam String scode) throws Exception {
        RespResult result = RespResult.fail();

        if (CommonUtil.checkMobile(phone) && (pword != null && pword.length() == 32)){
            // check sms code
            if (smsCodeLoginImpl.checkSmsCode(phone, scode)){
                // login logic
                User user = userService.userLogin(phone, pword);
                if (user != null){
                    // login success
                    // attach token to user, and return to front end
                    Map<String, Object> data = new HashMap<>();
                    data.put("uid", user.getId());
                    String jwtToken = jwtUtil.createJwt(data, 120);

                    result = RespResult.ok();
                    result.setAccessToken(jwtToken);

                    Map<String, Object> userInfo = new HashMap<>();
                    userInfo.put("uid", user.getId());
                    userInfo.put("phone", user.getPhone());
                    userInfo.put("name", user.getName());

                    result.setData(userInfo);

                } else {
                    result.setRCode(RCode.USER_LOGIN_ERROR);
                }
            } else {
                result.setRCode(RCode.SMS_CODE_ERROR);
            }
        } else {
            result.setRCode(RCode.PARAM_ERROR);
        }

        return result;
    }

    @PostMapping("/realname")
    @ApiOperation(value = "实名认证", notes = "提供手机号和姓名，身份证号，验证码进行实名认证")
    public RespResult userRealNameCheck(@RequestBody RealnameVO realnameVO) {
        RespResult result = RespResult.fail();
        //1 validate parameters
        if(CommonUtil.checkMobile(realnameVO.getPhone())){
            if(StringUtils.isNoneBlank(realnameVO.getName()) && StringUtils.isNoneBlank(realnameVO.getIdCard()) && StringUtils.isNoneBlank(realnameVO.getCode())) {
                //2 check sms code
                if (smsCodeRealNameImpl.checkSmsCode(realnameVO.getPhone(), realnameVO.getCode())){
                    // phone&code match
                    //3 check if user has finished real name check
                    User user = userService.queryByPhone(realnameVO.getPhone());
                    if (user != null && StringUtils.isBlank(user.getName())){
                        //4 real name check
                        boolean isValid = realNameService.realNameCheck(realnameVO.getName(), realnameVO.getIdCard());
                        if (isValid){
                            //5 store the real name and id card to database
                            user.setName(realnameVO.getName());
                            user.setIdCard(realnameVO.getIdCard());
                            int num = userService.userUpdate(user);
                            if (num == 1){
                                result = RespResult.ok();
                            } else {
                                result.setRCode(RCode.SERVER_ERROR);
                            }
                        } else {
                            result.setRCode(RCode.REAL_NAME_ERROR);
                        }

                    } else {
                        result.setRCode(RCode.REAL_NAME_FINISHED);
                    }
                } else {
                    result.setRCode(RCode.SMS_CODE_ERROR);
                }
            } else {
                result.setRCode(RCode.PARAM_ERROR);
            }
        }
        else {
            result.setRCode(RCode.PHONE_FORMAT_ERROR);
        }
        return result;
    }
}

