package com.celan.front.controller;

import com.celan.api.model.ProductInfo;
import com.celan.api.pojo.MultiProduct;
import com.celan.commom.enums.RCode;
import com.celan.commom.util.CommonUtil;
import com.celan.front.view.PageInfo;
import com.celan.front.view.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "产品信息")
@RestController
@RequestMapping("/v1")
public class ProductController extends BaseController{

    @ApiOperation(value = "查询首页产品信息", notes = "一个新手宝，三个优选，三个散标产品")
    @GetMapping("/product/index")
    public RespResult queryProductIndex(){
        RespResult result = RespResult.ok();
        MultiProduct multiProduct = productService.queryIndexPageProducts();
        result.setData(multiProduct);

        return result;
    }

    /*按产品类型分页查询*/
    @GetMapping("/product/list")
    public RespResult queryProductByType(@RequestParam("ptype") Integer pType,
                                         @RequestParam(value = "pageNo",required = false, defaultValue = "1") Integer pageNo,
                                         @RequestParam(value = "pageSize", required = false, defaultValue = "9") Integer pageSize){

        RespResult result = RespResult.fail();
        if (pType != null && (pType == 0 || pType == 1 || pType == 2)) {
            pageNo = CommonUtil.defaultPageNo(pageNo);
            pageSize = CommonUtil.defaultPageSize(pageSize);
            // 总记录数用于分页
            Integer recordNums = productService.queryRecordNumsByType(pType);
            if (recordNums > 0) {
                // 记录存在，查询产品集合
                List<ProductInfo> productInfos = productService.queryByTypeLimit(pType, pageNo, pageSize);
                result = RespResult.ok();
                result.setList(productInfos);

                // 分页信息
                PageInfo pageInfo = new PageInfo(pageNo, pageSize, recordNums);
                result.setPage(pageInfo);
            }
        }
        else {
            // 产品类型不存在
            result.setRCode(RCode.PARAM_PRODUCT_TYPE_ERROR);
        }
        return result;
    }
}
