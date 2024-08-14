package com.celan.dataservice.service;

import com.celan.api.pojo.BaseInfo;
import com.celan.api.service.PlatBaseInfoService;
import com.celan.dataservice.mapper.BidInfoMapper;
import com.celan.dataservice.mapper.ProductInfoMapper;
import com.celan.dataservice.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.math.BigDecimal;

@DubboService(interfaceClass = PlatBaseInfoService.class, version = "1.0")
public class PlatBaseInfoServiceImpl implements PlatBaseInfoService {
    // 注入mapper
    @Resource
    private UserMapper userMapper;

    @Resource
    private ProductInfoMapper productInfoMapper;

    @Resource
    private BidInfoMapper bidInfoMapper;

    /*平台基本信息*/
    public BaseInfo queryPlatBaseInfo() {
        // 获取注册人数，收益率平均值，累计成交金额
        int registerUsers = userMapper.selectCountUsers();

        BigDecimal avgRate = productInfoMapper.selectAvgRate();

        BigDecimal sumBidMoney = bidInfoMapper.selectSumBidMoney();

        BaseInfo baseInfo = new BaseInfo(avgRate, sumBidMoney, registerUsers);

        return baseInfo;
    }
}
