package com.xiyoukeji.beans;

import com.xiyoukeji.entity.FileEvaluate;
import com.xiyoukeji.utils.AssignType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasiy on 16/12/20.
 */
@AssignType
public class ProjectBean {
    private Integer id;
    private FileBean logo;
    private FileBean proposal;
    private FileBean fileEvaluate;
    private ProjectFoundationBean foundation;
    private String project_name;
    private String create_time;
    private String publish_time;
    private String invest_time;
    private String exit_time;
    private int state;
    private String project_introduction;
    private String project_code;
    private String project_stage;
    private String valuation;
    private ProjectUserBean createUser;
    private List<String> tab = new ArrayList<>();
    private List<ProjectUserBean> project_member = new ArrayList<>();
    private String project_reason;
    private String company_name;
    private String company_web;
    private String company_app;
    private String company_public;
    private String company_founder;
    private String company_tel;
    private String company_contact;
    private String contact_phone;
    private String finance_record;
    private String money_totalShare;
    private String money_eachShare;
    private String subscription_amount;
    private String subscription_money;
    private String project_resource;
    private String project_evaluates;
    private String project_address;
    private String project_introducer;
    private String project_introducer_tel;
    private String valuation_afterInvest;
    private String valuation_state;
    private String money_thisTime;
    private String share_common;
    private String investment_leader;
    private String enjoyor;
    private String investment_enjoyor;
    private String share_enjoyor;
    private String other;
    private String investment_other;
    private String share_other;

    private int project_schedule;
//    private List<NoteBean> project_notes = new ArrayList<>();
//    private List<EvaluateBean> project_evaluate = new ArrayList<>();
    private FileBean video;

    public FileBean getFileEvaluate() {
        return fileEvaluate;
    }

    public void setFileEvaluate(FileBean fileEvaluate) {
        this.fileEvaluate = fileEvaluate;
    }

    public String getProject_evaluates() {
        return project_evaluates;
    }

    public void setProject_evaluates(String project_evaluates) {
        this.project_evaluates = project_evaluates;
    }

    public String getProject_introducer_tel() {
        return project_introducer_tel;
    }

    public void setProject_introducer_tel(String project_introducer_tel) {
        this.project_introducer_tel = project_introducer_tel;
    }

    public FileBean getVideo() {
        return video;
    }

    public void setVideo(FileBean video) {
        this.video = video;
    }

    public String getProject_address() {
        return project_address;
    }

    public String getExit_time() {
        return exit_time;
    }

    public void setExit_time(String exit_time) {
        this.exit_time = exit_time;
    }

    public void setProject_address(String project_address) {
        this.project_address = project_address;
    }

    public ProjectUserBean getCreateUser() {
        return createUser;
    }

    public void setCreateUser(ProjectUserBean createUser) {
        this.createUser = createUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FileBean getLogo() {
        return logo;
    }

    public void setLogo(FileBean logo) {
        this.logo = logo;
    }

    public FileBean getProposal() {
        return proposal;
    }

    public void setProposal(FileBean proposal) {
        this.proposal = proposal;
    }

    public ProjectFoundationBean getFoundation() {
        return foundation;
    }

    public void setFoundation(ProjectFoundationBean foundation) {
        this.foundation = foundation;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_introduction() {
        return project_introduction;
    }

    public void setProject_introduction(String project_introduction) {
        this.project_introduction = project_introduction;
    }

    public String getProject_code() {
        return project_code;
    }

    public void setProject_code(String project_code) {
        this.project_code = project_code;
    }

    public String getProject_stage() {
        return project_stage;
    }

    public void setProject_stage(String project_stage) {
        this.project_stage = project_stage;
    }

    public String getValuation() {
        return valuation;
    }

    public void setValuation(String valuation) {
        this.valuation = valuation;
    }

    public List<String> getTab() {
        return tab;
    }

    public void setTab(List<String> tab) {
        this.tab = tab;
    }

    public List<ProjectUserBean> getProject_member() {
        return project_member;
    }

    public void setProject_member(List<ProjectUserBean> project_member) {
        this.project_member = project_member;
    }

    public String getProject_reason() {
        return project_reason;
    }

    public void setProject_reason(String project_reason) {
        this.project_reason = project_reason;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_web() {
        return company_web;
    }

    public void setCompany_web(String company_web) {
        this.company_web = company_web;
    }

    public String getCompany_app() {
        return company_app;
    }

    public void setCompany_app(String company_app) {
        this.company_app = company_app;
    }

    public String getCompany_public() {
        return company_public;
    }

    public void setCompany_public(String company_public) {
        this.company_public = company_public;
    }

    public String getCompany_founder() {
        return company_founder;
    }

    public void setCompany_founder(String company_founder) {
        this.company_founder = company_founder;
    }

    public String getCompany_tel() {
        return company_tel;
    }

    public void setCompany_tel(String company_tel) {
        this.company_tel = company_tel;
    }

    public String getCompany_contact() {
        return company_contact;
    }

    public void setCompany_contact(String company_contact) {
        this.company_contact = company_contact;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getFinance_record() {
        return finance_record;
    }

    public void setFinance_record(String finance_record) {
        this.finance_record = finance_record;
    }

    public String getMoney_totalShare() {
        return money_totalShare;
    }

    public void setMoney_totalShare(String money_totalShare) {
        this.money_totalShare = money_totalShare;
    }

    public String getMoney_eachShare() {
        return money_eachShare;
    }

    public void setMoney_eachShare(String money_eachShare) {
        this.money_eachShare = money_eachShare;
    }

    public String getSubscription_amount() {
        return subscription_amount;
    }

    public void setSubscription_amount(String subscription_amount) {
        this.subscription_amount = subscription_amount;
    }

    public String getSubscription_money() {
        return subscription_money;
    }

    public void setSubscription_money(String subscription_money) {
        this.subscription_money = subscription_money;
    }

    public String getProject_resource() {
        return project_resource;
    }

    public void setProject_resource(String project_resource) {
        this.project_resource = project_resource;
    }

    public String getProject_introducer() {
        return project_introducer;
    }

    public void setProject_introducer(String project_introducer) {
        this.project_introducer = project_introducer;
    }

    public String getValuation_afterInvest() {
        return valuation_afterInvest;
    }

    public void setValuation_afterInvest(String valuation_afterInvest) {
        this.valuation_afterInvest = valuation_afterInvest;
    }

    public String getValuation_state() {
        return valuation_state;
    }

    public void setValuation_state(String valuation_state) {
        this.valuation_state = valuation_state;
    }

    public String getMoney_thisTime() {
        return money_thisTime;
    }

    public void setMoney_thisTime(String money_thisTime) {
        this.money_thisTime = money_thisTime;
    }

    public String getShare_common() {
        return share_common;
    }

    public void setShare_common(String share_common) {
        this.share_common = share_common;
    }

    public String getInvestment_leader() {
        return investment_leader;
    }

    public void setInvestment_leader(String investment_leader) {
        this.investment_leader = investment_leader;
    }

    public String getEnjoyor() {
        return enjoyor;
    }

    public void setEnjoyor(String enjoyor) {
        this.enjoyor = enjoyor;
    }

    public String getInvestment_enjoyor() {
        return investment_enjoyor;
    }

    public void setInvestment_enjoyor(String investment_enjoyor) {
        this.investment_enjoyor = investment_enjoyor;
    }

    public String getShare_enjoyor() {
        return share_enjoyor;
    }

    public void setShare_enjoyor(String share_enjoyor) {
        this.share_enjoyor = share_enjoyor;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getInvestment_other() {
        return investment_other;
    }

    public void setInvestment_other(String investment_other) {
        this.investment_other = investment_other;
    }

    public String getShare_other() {
        return share_other;
    }

    public void setShare_other(String share_other) {
        this.share_other = share_other;
    }

    public int getProject_schedule() {
        return project_schedule;
    }

    public void setProject_schedule(int project_schedule) {
        this.project_schedule = project_schedule;
    }
//
//    public List<NoteBean> getProject_notes() {
//        return project_notes;
//    }
//
//    public void setProject_notes(List<NoteBean> project_notes) {
//        this.project_notes = project_notes;
//    }
//
//    public List<EvaluateBean> getProject_evaluate() {
//        return project_evaluate;
//    }
//
//    public void setProject_evaluate(List<EvaluateBean> project_evaluate) {
//        this.project_evaluate = project_evaluate;
//    }

}
