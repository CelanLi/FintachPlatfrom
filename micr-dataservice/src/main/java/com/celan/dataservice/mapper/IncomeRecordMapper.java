package com.celan.dataservice.mapper;

import com.celan.api.model.IncomeRecord;

public interface IncomeRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(IncomeRecord record);

    int insertSelective(IncomeRecord record);

    IncomeRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(IncomeRecord record);

    int updateByPrimaryKey(IncomeRecord record);
}