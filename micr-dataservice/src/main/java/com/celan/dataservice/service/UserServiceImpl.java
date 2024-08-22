package com.celan.dataservice.service;

import com.celan.api.model.User;
import com.celan.api.service.UserService;
import com.celan.commom.util.CommonUtil;
import com.celan.dataservice.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService(interfaceClass = UserService.class, version = "1.0")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User queryByPhone(String phone) {
        User user = null;
        if (CommonUtil.checkMobile(phone)) {
            user = userMapper.selectByPhone(phone);
        }
        return user;
    }
}
