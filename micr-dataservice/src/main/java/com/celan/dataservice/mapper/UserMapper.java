package com.celan.dataservice.mapper;

import com.celan.api.model.User;
import com.celan.api.pojo.UserAccountInfo;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int selectCountUsers();

    int deleteByPrimaryKey(Long id);


    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByPhone(@Param("phone") String phone);

    int insertReturnPrimaryKey(User record);

    User selectLogin(@Param("phone") String phone, @Param("password") String password);

    UserAccountInfo selectUserAccountById(@Param("uid") Integer uid);
}