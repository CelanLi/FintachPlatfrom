package com.celan.api.service;

import com.celan.api.model.User;

public interface UserService {
    /*根据手机号查询数据*/
    User queryByPhone(String phone);
}
