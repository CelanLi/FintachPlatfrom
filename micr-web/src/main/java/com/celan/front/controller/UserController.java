package com.celan.front.controller;

import com.celan.api.model.User;
import com.celan.commom.enums.RCode;
import com.celan.commom.util.CommonUtil;
import com.celan.front.view.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@Api(tags = "用户接口")
public class UserController extends BaseController{

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
}
