package com.celan.front.controller;

import com.celan.api.model.RechargeRecord;
import com.celan.front.view.RespResult;
import com.celan.front.view.recharge.ResultView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "充值接口")
public class RechargeController extends BaseController{

    // recharge record list
    @ApiOperation(value = "充值记录", notes = "充值记录")
    @GetMapping("/v1/recharge/records")
    public RespResult queryRechargePage(@RequestHeader("uid") Integer uid,
                                         @RequestParam(required = false, defaultValue = "1") Integer pageNo,
                                         @RequestParam(required = false, defaultValue = "6") Integer pageSize){
        RespResult result = RespResult.fail();
        if (uid != null){
            List<RechargeRecord> rechargeRecordList = rechargeService.queryByUid(uid, pageNo, pageSize);
            if (rechargeRecordList != null){
                result = RespResult.ok();
                result.setList(toView(rechargeRecordList));
                // no pagination information, like total count, current page, etc.
            }
        }
        return result;
    }

    private List<ResultView> toView(List<RechargeRecord> src){
        List<ResultView> target = new ArrayList<>();
        src.forEach(record->{
            target.add(new ResultView(record));
        });
        return target;
    }
}
