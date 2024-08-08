package com.celan.api.service;

import com.celan.api.pojo.BaseInfo;

public interface PlatBaseInfoService {
    /**
     * 计算利率，注册人数，累计成交金额
     * @return
     */
    BaseInfo queryPlatBaseInfo();
}
