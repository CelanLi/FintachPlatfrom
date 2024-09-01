package com.celan.dataservice.service;

import com.celan.api.model.FinanceAccount;
import com.celan.api.model.User;
import com.celan.api.service.UserService;
import com.celan.commom.util.CommonUtil;
import com.celan.dataservice.mapper.FinanceAccountMapper;
import com.celan.dataservice.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@DubboService(interfaceClass = UserService.class, version = "1.0")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private FinanceAccountMapper financeAccountMapper;

    @Value("${ylb.config.password-salt}")
    private String passwordSalt;

    @Override
    public User queryByPhone(String phone) {
        User user = null;
        if (CommonUtil.checkMobile(phone)) {
            user = userMapper.selectByPhone(phone);
        }
        return user;
    }

    @Override
    // transaction, if any error occurs, rollback
    @Transactional(rollbackFor = Exception.class)
    // synchronized, only one thread can access this method at a time, thus no duplicate phone number
    public synchronized int userRegister(String phone, String password) {
        int result = 0;
        if (CommonUtil.checkMobile(phone) && (password != null && password.length() == 32)) {
            // check if the phone number already exists
            if (userMapper.selectByPhone(phone) == null) {
                User user = new User();
                user.setPhone(phone);


                // here the password is encrypted using md5, but we have to add salt to it
                String saltedPassword = DigestUtils.md5DigestAsHex((password + passwordSalt).getBytes());
                user.setLoginPassword(saltedPassword);
                user.setAddTime(new Date());
                userMapper.insertReturnPrimaryKey(user);

                // add finance account for user account
                FinanceAccount account = new FinanceAccount();
                account.setUid(user.getId());
                account.setAvailableMoney(new BigDecimal("0"));
                financeAccountMapper.insertSelective(account);

                result = 1;
            } else{
                // phone number already exists
                result = 2;
            }
        }

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public User userLogin(String phone, String password) {
        User user = new User();
        if (CommonUtil.checkMobile(phone) && (password != null && password.length() == 32)) {
            String saltedPassword = DigestUtils.md5DigestAsHex((password + passwordSalt).getBytes());
            user = userMapper.selectLogin(phone, saltedPassword);
            // update last login time
            if (user != null) {
                user.setLastLoginTime(new Date());
                userMapper.updateByPrimaryKeySelective(user);
            }
        }
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int userUpdate(User user) {
        if (user != null && user.getId() != null) {
            return userMapper.updateByPrimaryKeySelective(user);
        }
        return 0;
    }
}
