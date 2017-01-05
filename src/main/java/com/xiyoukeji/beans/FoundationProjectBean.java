package com.xiyoukeji.beans;

import com.xiyoukeji.utils.AssignType;

/**
 * Created by dasiy on 16/12/20.
 */
@AssignType
public class FoundationProjectBean {
    private Integer id;
    private String project_name;
    private String create_time;
    private String publish_time;
    private String invest_time;
    private String exit_time;
    private int state;
    private int exitState;
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

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getInvest_time() {
        return invest_time;
    }

    public void setInvest_time(String invest_time) {
        this.invest_time = invest_time;
    }

    public String getExit_time() {
        return exit_time;
    }

    public void setExit_time(String exit_time) {
        this.exit_time = exit_time;
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
