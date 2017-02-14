package com.xiyoukeji.beans;

import com.xiyoukeji.utils.AssignType;

import javax.persistence.Entity;

/**
 * Created by dasiy on 17/2/14.
 */
@AssignType
public class Statistics {
    private String foundation_id;
    private String foundation_name;
    private String valuation_afterInvest = "0";
    private String project_count = "0";
    private String money_thisTime = "0";

    public String getMoney_thisTime() {
        return money_thisTime;
    }

    public void setMoney_thisTime(String money_thisTime) {
        this.money_thisTime = money_thisTime;
    }

    public String getFoundation_id() {
        return foundation_id;
    }

    public void setFoundation_id(String foundation_id) {
        this.foundation_id = foundation_id;
    }

    public String getFoundation_name() {
        return foundation_name;
    }

    public void setFoundation_name(String foundation_name) {
        this.foundation_name = foundation_name;
    }

    public String getValuation_afterInvest() {
        return valuation_afterInvest;
    }

    public void setValuation_afterInvest(String valuation_afterInvest) {
        this.valuation_afterInvest = valuation_afterInvest;
    }

    public String getProject_count() {
        return project_count;
    }

    public void setProject_count(String project_count) {
        this.project_count = project_count;
    }
}
