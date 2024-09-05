package com.celan.dataservice.service;

import com.celan.api.model.RechargeRecord;
import com.celan.api.service.RechargeService;
import com.celan.commom.util.CommonUtil;
import com.celan.dataservice.mapper.RechargeRecordMapper;
import org.apache.dubbo.config.annotation.DubboService;

import com.celan.api.service.RechargeService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@DubboService(interfaceClass = RechargeService.class, version = "1.0")
public class RechargeServiceImpl implements RechargeService {
    @Resource
    private RechargeRecordMapper rechargeRecordMapper;

    @Override
    public List<RechargeRecord> queryByUid(Integer uid, Integer pageNo, Integer pageSize) {
        List<RechargeRecord> records = new ArrayList<>();
        if (uid != null && uid > 0) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            int offset = (pageNo - 1) * pageSize;
            records = rechargeRecordMapper.selectByUid(uid, offset, pageSize);
            return rechargeRecordMapper.selectByUid(uid, (pageNo - 1) * pageSize, pageSize);
        }
        return null;
    }
}
