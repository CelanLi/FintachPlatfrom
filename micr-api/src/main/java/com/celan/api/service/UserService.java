package com.celan.api.service;

import com.celan.api.model.User;
import com.celan.api.pojo.UserAccountInfo;

public interface UserService {
    User queryByPhone(String phone);

    int userRegister(String phone, String password);

    User userLogin(String phone, String password);

    int userUpdate(User user);

    // get user and finance info
    UserAccountInfo queryUserAllInfo(Integer uid);

    User queryById(Integer uid);
}
