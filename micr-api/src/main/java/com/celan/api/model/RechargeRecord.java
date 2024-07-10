package com.celan.api.model;

import java.math.BigDecimal;
import java.util.Date;

public class RechargeRecord {
    private Long id;

    private Long uid;

    private String rechargeNo;

    private Long rechargeStatus;

    private BigDecimal rechargeMoney;

    private Date rechargeTime;

    private String rechargeDesc;

    private String channel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getRechargeNo() {
        return rechargeNo;
    }

    public void setRechargeNo(String rechargeNo) {
        this.rechargeNo = rechargeNo;
    }

    public Long getRechargeStatus() {
        return rechargeStatus;
    }

    public void setRechargeStatus(Long rechargeStatus) {
        this.rechargeStatus = rechargeStatus;
    }

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public String getRechargeDesc() {
        return rechargeDesc;
    }

    public void setRechargeDesc(String rechargeDesc) {
        this.rechargeDesc = rechargeDesc;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}