package com.celan.dataservice.mapper;

import com.celan.api.model.BidInfo;

import java.math.BigDecimal;

public interface BidInfoMapper {

    /*累计成交金额*/
    BigDecimal selectSumBidMoney();
    int deleteByPrimaryKey(Long id);

    int insert(BidInfo record);

    int insertSelective(BidInfo record);

    BidInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BidInfo record);

    int updateByPrimaryKey(BidInfo record);
}