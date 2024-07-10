package com.celan.api.model;

import java.math.BigDecimal;
import java.util.Date;

public class BidInfo {
    private Long id;

    private Long loanId;

    private Long uid;

    private BigDecimal bidMoney;

    private Date bidTime;

    private Long bidStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public BigDecimal getBidMoney() {
        return bidMoney;
    }

    public void setBidMoney(BigDecimal bidMoney) {
        this.bidMoney = bidMoney;
    }

    public Date getBidTime() {
        return bidTime;
    }

    public void setBidTime(Date bidTime) {
        this.bidTime = bidTime;
    }

    public Long getBidStatus() {
        return bidStatus;
    }

    public void setBidStatus(Long bidStatus) {
        this.bidStatus = bidStatus;
    }
}