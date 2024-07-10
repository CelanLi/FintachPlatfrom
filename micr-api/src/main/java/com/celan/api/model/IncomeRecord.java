package com.celan.api.model;

import java.math.BigDecimal;
import java.util.Date;

public class IncomeRecord {
    private Long id;

    private Long uid;

    private Long prodId;

    private Long bidId;

    private BigDecimal bitMoney;

    private Date incomeDate;

    private BigDecimal incomeMoney;

    private Long incomeStatus;

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

    public Long getProdId() {
        return prodId;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
        this.bidId = bidId;
    }

    public BigDecimal getBitMoney() {
        return bitMoney;
    }

    public void setBitMoney(BigDecimal bitMoney) {
        this.bitMoney = bitMoney;
    }

    public Date getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    public BigDecimal getIncomeMoney() {
        return incomeMoney;
    }

    public void setIncomeMoney(BigDecimal incomeMoney) {
        this.incomeMoney = incomeMoney;
    }

    public Long getIncomeStatus() {
        return incomeStatus;
    }

    public void setIncomeStatus(Long incomeStatus) {
        this.incomeStatus = incomeStatus;
    }
}