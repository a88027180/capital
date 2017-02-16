package com.xiyoukeji.beans;

import com.xiyoukeji.utils.AssignType;

/**
 * Created by dasiy on 17/2/15.
 */
@AssignType
public class SearchStatistic {
    private String foundationList;
    private String city;
    private Integer vocationId;
    private Integer userId;
    private Integer foundationId;

    public Integer getFoundationId() {
        return foundationId;
    }

    public void setFoundationId(Integer foundationId) {
        this.foundationId = foundationId;
    }

    public String getFoundationList() {
        return foundationList;
    }

    public void setFoundationList(String foundationList) {
        this.foundationList = foundationList;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getVocationId() {
        return vocationId;
    }

    public void setVocationId(Integer vocationId) {
        this.vocationId = vocationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
