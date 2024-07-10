package com.celan.dataservice.mapper;

import com.celan.api.model.BidInfo;

public interface BidInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BidInfo record);

    int insertSelective(BidInfo record);

    BidInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BidInfo record);

    int updateByPrimaryKey(BidInfo record);
}