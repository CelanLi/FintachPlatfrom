package com.celan.dataservice.mapper;

import com.celan.api.model.BidInfo;
import com.celan.api.pojo.BidInfoProduct;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface BidInfoMapper {

    /*累计成交金额*/
    BigDecimal selectSumBidMoney();
    int deleteByPrimaryKey(Long id);

    int insert(BidInfo record);

    int insertSelective(BidInfo record);

    BidInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BidInfo record);

    int updateByPrimaryKey(BidInfo record);

    /*通过产品id查询投资记录*/
    List<BidInfoProduct> selectByProductId(@Param("productId") Integer productId,
                                           @Param("offset") int offset,
                                           @Param("rows") Integer rows);
}