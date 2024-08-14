package com.celan.front.controller;

import com.celan.api.pojo.BaseInfo;
import com.celan.front.view.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "平台基本信息")
@RestController
@RequestMapping("/v1")
public class PlatInfoController extends BaseController {
//    平台基本信息
    @ApiOperation(value = "查询平台基本信息", notes = "注册人数，" +
            "累计成交金额，" +
            "平均利率")
    @GetMapping("/plat/info")
    public RespResult queryPlatBaseInfo(){
        // 调用服务
        BaseInfo baseInfo = platBaseInfoService.queryPlatBaseInfo();

        RespResult result = new RespResult();
        result.setCode(1000); // 表示成功
        result.setMsg("查询平台信息成功");
        result.setData(baseInfo);

        return result;
    }
}
