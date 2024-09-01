package com.celan.front.controller;

import com.celan.front.service.RealNameService;
import com.celan.front.view.RespResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "实名认证接口")
public class RealNameController extends BaseController{

    @Autowired
    private RealNameService realNameService;

    @GetMapping("/realName/check")
    public RespResult realNameCheck(String name, String idCard) {
        RespResult response = RespResult.fail();
        Boolean result = realNameService.realNameCheck(name, idCard);
        if (result){
            response = RespResult.ok();
        }
        return response;
    }
}
