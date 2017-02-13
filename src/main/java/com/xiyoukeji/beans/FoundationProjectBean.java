package com.xiyoukeji.beans;

import com.xiyoukeji.utils.AssignType;

/**
 * Created by dasiy on 16/12/20.
 */
@AssignType
public class FoundationProjectBean {
    private Integer id;
    private String project_name;
    private long create_current;
    private long publish_current;
    private long invest_current;
    private long exit_current;
    private int state;
    private int exitState;
    private Integer province_id;
    private Integer city_id;

    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public long getCreate_current() {
        return create_current;
    }

    public void setCreate_current(long create_current) {
        this.create_current = create_current;
    }

    public long getPublish_current() {
        return publish_current;
    }

    public void setPublish_current(long publish_current) {
        this.publish_current = publish_current;
    }

    public long getInvest_current() {
        return invest_current;
    }

    public void setInvest_current(long invest_current) {
        this.invest_current = invest_current;
    }

    public long getExit_current() {
        return exit_current;
    }

    public void setExit_current(long exit_current) {
        this.exit_current = exit_current;
    }
    //    private String project_introduction;
//    private String project_code;
//    private String project_stage;
//    private String valuation;
//    private String project_reason;
//    private String company_name;
//    private String company_web;
//    private String company_app;
//    private String company_public;
//    private String company_founder;
//    private String company_tel;
//    private String company_contact;
//    private String contact_phone;
//    private String finance_record;
//    private String money_totalShare;
//    private String money_eachShare;
//    private String subscription_amount;
//    private String subscription_money;
//    private String project_resource;
//    private String project_address;
//    private String project_introducer;
//    private String valuation_afterInvest;
//    private String valuation_state;
//    private String money_thisTime;
//    private String share_common;
//    private String investment_leader;
//    private String enjoyor;
//    private String investment_enjoyor;
//    private String share_enjoyor;
//    private String other;
//    private String investment_other;
//    private String share_other;

    private int project_schedule;

    public int getExitState() {
        return exitState;
    }

    public void setExitState(int exitState) {
        this.exitState = exitState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getProject_schedule() {
        return project_schedule;
    }

    public void setProject_schedule(int project_schedule) {
        this.project_schedule = project_schedule;
    }
}
