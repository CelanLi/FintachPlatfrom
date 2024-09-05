package com.celan.front.controller;

import com.celan.api.model.User;
import com.celan.api.pojo.UserAccountInfo;
import com.celan.commom.constants.RedisKey;
import com.celan.commom.enums.RCode;
import com.celan.commom.util.CommonUtil;
import com.celan.front.view.RespResult;
import com.celan.front.view.invest.RankView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
* 有关投资的功能
* */
@Api(tags = "投资信息")
@RestController
public class InvestController extends BaseController{

    /*rank of investment*/
    @ApiOperation(value = "查询投资排行榜", notes = "查询投资排行榜前三名")
    @GetMapping("/v1/invest/rank")
    public RespResult showInvestRank(){
        // 从redis查询数据
        Set<ZSetOperations.TypedTuple<String>> sets = stringRedisTemplate
                .boundZSetOps(RedisKey.KEY_INVEST_RANK).reverseRangeWithScores(0, 2);
        List<RankView> rankList = new ArrayList<>();
        // 遍历set集合
        sets.forEach( tuple -> {
            rankList.add(new RankView(CommonUtil.maskMobile(tuple.getValue()), tuple.getScore()));
        });

        RespResult result = RespResult.ok();
        result.setList(rankList);
        return result;
    }

    /*invest product, update investment rank*/
    /*user can only invest more than 100 yuan and mod 100 must be integer*/
    @PostMapping("/v1/invest/product")
    @ApiOperation(value = "投资产品", notes = "投资产品")
    public RespResult investProduct(@RequestHeader("uid") Integer uid,
                                    @RequestParam("productId") Integer productId,
                                    @RequestParam("money") BigDecimal money){
        RespResult result = RespResult.fail();
        if ((uid != null && uid > 0) && (productId != null && productId > 0)
                && (money != null && money.intValue() % 100 == 0 && money.intValue() >= 100)){
            int investResult = investService.investProduct(uid, productId, money);
            switch (investResult){
                case 1:
                    modifyInvestRank(uid, money);
                    result = RespResult.ok();
                    break;
                case 0:
                    result.setRCode(RCode.PARAM_ERROR);
                    break;
                case 2:
                    result.setMsg("账户不存在");
                    break;
                case 3:
                    result.setMsg("余额不足");
                    break;
                case 4:
                    result.setMsg("产品不存在或您的投资金额超过剩余可投资金额");
                    break;
            }
        }
        else {
            result.setRCode(RCode.PARAM_ERROR);
        }
        return result;
    }

    // update investment rank
    private void modifyInvestRank(Integer uid, BigDecimal money){
        //1 get user mobile
        User user = userService.queryById(uid);
        if (user != null){
            //2 update investment rank
            String key = RedisKey.KEY_INVEST_RANK;
            stringRedisTemplate.boundZSetOps(key).incrementScore(user.getPhone(), money.doubleValue());
        }

    }
}
