package com.celan.api.pojo;

import com.celan.api.model.ProductInfo;

import java.io.Serializable;
import java.util.List;

/*
* 多个产品数据
* */
public class MultiProduct implements Serializable {
    private List<ProductInfo> xinShouBao;

    public List<ProductInfo> getXinShouBao() {
        return xinShouBao;
    }

    public void setXinShouBao(List<ProductInfo> xinShouBao) {
        this.xinShouBao = xinShouBao;
    }

    public List<ProductInfo> getYouXuan() {
        return youXuan;
    }

    public void setYouXuan(List<ProductInfo> youXuan) {
        this.youXuan = youXuan;
    }

    public List<ProductInfo> getSanBiao() {
        return sanBiao;
    }

    public void setSanBiao(List<ProductInfo> sanBiao) {
        this.sanBiao = sanBiao;
    }

    private List<ProductInfo> youXuan;

    private List<ProductInfo> sanBiao;
}
