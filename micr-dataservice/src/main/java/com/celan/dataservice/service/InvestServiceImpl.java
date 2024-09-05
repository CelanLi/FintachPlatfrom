package com.celan.dataservice.service;

import com.celan.api.model.BidInfo;
import com.celan.api.model.FinanceAccount;
import com.celan.api.model.ProductInfo;
import com.celan.api.pojo.BidInfoProduct;
import com.celan.api.service.InvestService;
import com.celan.commom.constants.YLBConstant;
import com.celan.commom.util.CommonUtil;
import com.celan.dataservice.mapper.BidInfoMapper;
import com.celan.dataservice.mapper.FinanceAccountMapper;
import com.celan.dataservice.mapper.ProductInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DubboService(interfaceClass = InvestService.class, version = "1.0")
public class InvestServiceImpl implements InvestService {
    @Resource
    private BidInfoMapper bidInfoMapper;

    @Resource
    private FinanceAccountMapper financeAccountMapper;

    @Resource
    private ProductInfoMapper productInfoMapper;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int investProduct(Integer uid, Integer productId, BigDecimal money) {
        int result = 0; // param error
        //1 validate params
        if ((uid != null && uid > 0) && (productId != null && productId > 0)
                && (money != null && money.intValue() % 100 == 0 && money.intValue() >= 100)) {
            //2 check account balance
            FinanceAccount account = financeAccountMapper.selectByUidForUpdate(uid);
            if (account != null){
                if (CommonUtil.ge(account.getAvailableMoney(), money)) {
                    //3 check if the product can be invested
                    long productIdLong = productId.longValue();
                    ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(productIdLong);
                    if (productInfo != null && productInfo.getProductStatus() == YLBConstant.PRODUCT_STATUS_SELLING
                            && CommonUtil.ge(productInfo.getLeftProductMoney(), money) ){
                        if (
                        CommonUtil.ge(productInfo.getBidMaxLimit(), money) &&
                        CommonUtil.ge(money, productInfo.getBidMinLimit())){
                            //4 update account balance
                            int rows = financeAccountMapper.updateAvailableMoneyByInvest(uid, money);
                            if (rows<1){
                                throw new RuntimeException("update account balance failed");
                            }
                            //5 update product info
                            rows = productInfoMapper.updateLeftProductMoney(productIdLong, money);
                            if (rows<1){
                                throw new RuntimeException("update product info failed");
                            }
                            //6 create bid info
                            BidInfo bidInfo = new BidInfo();
                            bidInfo.setBidMoney(money);
                            bidInfo.setLoanId(productIdLong);
                            bidInfo.setUid(uid.longValue());
                            bidInfo.setBidStatus(YLBConstant.INVEST_STATUS_SUCCESS);
                            bidInfo.setBidTime(new Date());
                            bidInfoMapper.insert(bidInfo);

                            //7 update the product sold out
                            ProductInfo dbProductInfo = productInfoMapper.selectByPrimaryKey(productIdLong);
                            if (dbProductInfo.getLeftProductMoney().compareTo(new BigDecimal("0")) == 0){
                                rows = productInfoMapper.updateSelled(productIdLong);
                                if (rows<1){
                                    throw new RuntimeException("update product status failed");
                                }
                            }

                            result = 1;
                        }
                    }else {
                        result = 4; // product not exist or has been sold out
                    }
                } else {
                    result = 3; // insufficient balance
                }
            } else {
                result = 2; // account not exist
            }
        } else {
            result = 0; // param error
        }
        return result;
    }
}
