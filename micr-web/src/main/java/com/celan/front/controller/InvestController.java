package com.celan.front.controller;

import com.celan.commom.constants.RedisKey;
import com.celan.commom.util.CommonUtil;
import com.celan.front.view.RespResult;
import com.celan.front.view.invest.RankView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
* 有关投资的功能
* */
@Api(tags = "投资信息")
@RestController
public class InvestController extends BaseController{

    /*投资排行榜*/
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
}
