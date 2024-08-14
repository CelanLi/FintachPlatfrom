package com.celan.dataservice.mapper;

import com.celan.api.model.User;

public interface UserMapper {
    //统计注册人数
    int selectCountUsers();

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}