package com.celan.api.service;

import com.celan.api.pojo.BidInfoProduct;

import java.math.BigDecimal;
import java.util.List;

public interface InvestService {
    /*get invest record of a product*/
    List<BidInfoProduct> queryBidListByProductId(Integer productId, Integer pageNo, Integer pageSize);

    /*if return value is 1, investment success*/
    int investProduct(Integer uid, Integer productId, BigDecimal money);
}
