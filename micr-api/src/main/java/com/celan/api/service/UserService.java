package com.celan.api.service;

import com.celan.api.model.User;

public interface UserService {
    User queryByPhone(String phone);

    int userRegister(String phone, String password);

    User userLogin(String phone, String password);

    int userUpdate(User user);
}
