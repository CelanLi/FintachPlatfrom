package com.celan.dataservice.mapper;

import com.celan.api.model.FinanceAccount;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface FinanceAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinanceAccount record);

    int insertSelective(FinanceAccount record);

    FinanceAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinanceAccount record);

    int updateByPrimaryKey(FinanceAccount record);

    FinanceAccount selectByUidForUpdate(@Param("uid") Integer uid);

    int updateAvailableMoneyByInvest(@Param("uid") Integer uid, @Param("money") BigDecimal money);
}