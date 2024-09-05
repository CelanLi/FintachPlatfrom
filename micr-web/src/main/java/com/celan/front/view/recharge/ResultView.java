package com.celan.front.view.recharge;

import com.celan.api.model.RechargeRecord;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.math.BigDecimal;

public class ResultView {
    private Long id;
    private String result= "未知";
    private String rechargeDate = "-";
    private BigDecimal rechargeMoney;

    public ResultView(RechargeRecord record) {
        this.id = record.getId();
        this.rechargeMoney = record.getRechargeMoney();

        if(record.getRechargeTime() != null){
            rechargeDate = DateFormatUtils.format(record.getRechargeTime(), "yyyy-MM-dd");
        }

        switch (record.getRechargeStatus()){
            case 0:
                result = "充值中";
                break;
            case 1:
                result = "充值成功";
                break;
            case 2:
                result = "充值失败";
                break;
        }
    }

    public Long getId() {
        return id;
    }

    public String getResult() {
        return result;
    }

    public String getRechargeDate() {
        return rechargeDate;
    }

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public ResultView(Long id, String result, String rechargeDate, BigDecimal rechargeMoney) {
        this.id = id;
        this.result = result;
        this.rechargeDate = rechargeDate;
        this.rechargeMoney = rechargeMoney;
    }
}
