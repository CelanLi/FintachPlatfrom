package com.celan.api.service;

import com.celan.api.model.ProductInfo;
import com.celan.api.pojo.MultiProduct;

import java.util.List;

public interface ProductService {
    /*
    * 根据产品类型，返回产品信息列表
    * */

    List<ProductInfo> queryByTypeLimit(Integer ptype, Integer offset, Integer rows);

    /*首页多个产品数据*/
    MultiProduct queryIndexPageProducts();

    /*某个产品类型的记录总数*/
    Integer queryRecordNumsByType(Integer pType);

    /*根据产品id查询产品详情*/
    ProductInfo queryProductById(Integer id);
}
