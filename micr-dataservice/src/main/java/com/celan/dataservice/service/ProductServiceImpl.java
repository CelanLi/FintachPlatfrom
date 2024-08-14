package com.celan.dataservice.service;

import com.celan.api.model.ProductInfo;
import com.celan.api.pojo.MultiProduct;
import com.celan.api.service.PlatBaseInfoService;
import com.celan.api.service.ProductService;
import com.celan.commom.constants.YLBConstant;
import com.celan.commom.util.CommonUtil;
import com.celan.dataservice.mapper.ProductInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@DubboService(interfaceClass = ProductService.class, version = "1.0")
public class ProductServiceImpl implements ProductService{

    @Resource
    private ProductInfoMapper productInfoMapper;

    /*按类型分页查询产品记录*/
    @Override
    public List<ProductInfo> queryByTypeLimit(Integer pType, Integer pageNo, Integer pageSize) {
        List<ProductInfo> productInfos = new ArrayList<>();
        /*校验参数*/
        if (pType == 0 || pType == 1 || pType == 2) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            int offset = (pageNo - 1) * pageSize;
            productInfos = productInfoMapper.selectByTypeLimit(pType, offset, pageSize);
        }
        return productInfos;
    }

    /*首页多个产品数据*/
    @Override
    public MultiProduct queryIndexPageProducts() {
        MultiProduct multiProduct = new MultiProduct();
        //查询新手宝
        List<ProductInfo> xinShouBao = productInfoMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_XINSHOUBAO_0, 0, 1);
        //查询优选
        List<ProductInfo> youXuan = productInfoMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_YOUXUAN_1 ,0,3);
        //查询散标
        List<ProductInfo> sanBiao = productInfoMapper.selectByTypeLimit(YLBConstant.PRODUCT_TYPE_SANBIAO_2, 0, 3);
        multiProduct.setXinShouBao(xinShouBao);
        multiProduct.setYouXuan(youXuan);
        multiProduct.setSanBiao(sanBiao);
        return multiProduct;
    }

    /*某个产品类型的记录总数*/
    @Override
    public Integer queryRecordNumsByType(Integer pType) {
        Integer counts = 0;
        if (pType == 0 || pType == 1 || pType == 2) {
            counts = productInfoMapper.selectCountByType(pType);
        }
        return counts;
    }
}
