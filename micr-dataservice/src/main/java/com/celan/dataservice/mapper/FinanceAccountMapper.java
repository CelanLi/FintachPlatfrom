package com.celan.dataservice.mapper;

import com.celan.api.model.FinanceAccount;

public interface FinanceAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FinanceAccount record);

    int insertSelective(FinanceAccount record);

    FinanceAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FinanceAccount record);

    int updateByPrimaryKey(FinanceAccount record);
}