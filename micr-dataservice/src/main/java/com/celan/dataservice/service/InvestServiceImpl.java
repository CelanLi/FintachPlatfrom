package com.celan.dataservice.service;

import com.celan.api.pojo.BidInfoProduct;
import com.celan.api.service.InvestService;
import com.celan.commom.util.CommonUtil;
import com.celan.dataservice.mapper.BidInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@DubboService(interfaceClass = InvestService.class, version = "1.0")
public class InvestServiceImpl implements InvestService {
    @Resource
    private BidInfoMapper bidInfoMapper;
    @Override
    public List<BidInfoProduct> queryBidListByProductId(Integer productId, Integer pageNo, Integer pageSize) {
        List<BidInfoProduct> bidInfoProducts = new ArrayList<>();
        if (productId != null && productId > 0) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            int offset = (pageNo - 1) * pageSize;
            bidInfoProducts = bidInfoMapper.selectByProductId(productId, offset, pageSize);
        }
        return bidInfoProducts;
    }
}
