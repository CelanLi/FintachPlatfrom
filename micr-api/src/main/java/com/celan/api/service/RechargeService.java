package com.celan.api.service;

import com.celan.api.model.RechargeRecord;

import java.util.List;

public interface RechargeService {

    // select recharge record by user id
    List<RechargeRecord> queryByUid(Integer uid, Integer pageNo, Integer pageSize);
}
