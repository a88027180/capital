package com.xiyoukeji.beans;

import com.xiyoukeji.entity.*;
import com.xiyoukeji.utils.AssignType;

import javax.persistence.ManyToMany;
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
    private EvaluateAvgBean evaluateAvg;
    private ProjectFoundationBean foundation;
    private String project_name;
    private long create_current;
    private long publish_current;
    private long invest_current;
    private long exit_current;
    private int state;
    private int exitState;
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
    private Integer province_id;
    private Integer city_id;
    private String city_name;
    private String province_name;
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
    private List<InvestmentOtherBean> investment_others = new ArrayList<>();
    private int project_schedule;
    private String sign_date;
    private String pay_date;
    private String pay_money;
    private List<VocationOneBean> vocationOnes = new ArrayList<>();
    private List<VocationTwoBean> vocationTwos = new ArrayList<>();
    private List<VocationThreeBean> vocationThrees = new ArrayList<>();
    //    private List<NoteBean> project_notes = new ArrayList<>();
//    private List<EvaluateBean> project_evaluate = new ArrayList<>();
    private FileBean video;

    public List<VocationOneBean> getVocationOnes() {
        return vocationOnes;
    }

    public void setVocationOnes(List<VocationOneBean> vocationOnes) {
        this.vocationOnes = vocationOnes;
    }

    public List<VocationTwoBean> getVocationTwos() {
        return vocationTwos;
    }

    public void setVocationTwos(List<VocationTwoBean> vocationTwos) {
        this.vocationTwos = vocationTwos;
    }

    public List<VocationThreeBean> getVocationThrees() {
        return vocationThrees;
    }

    public void setVocationThrees(List<VocationThreeBean> vocationThrees) {
        this.vocationThrees = vocationThrees;
    }

    public Integer getProvince_id() {
        return province_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
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

    public List<InvestmentOtherBean> getInvestment_others() {
        return investment_others;
    }

    public void setInvestment_others(List<InvestmentOtherBean> investment_others) {
        this.investment_others = investment_others;
    }

    public String getSign_date() {
        return sign_date;
    }

    public void setSign_date(String sign_date) {
        this.sign_date = sign_date;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public String getPay_money() {
        return pay_money;
    }

    public void setPay_money(String pay_money) {
        this.pay_money = pay_money;
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

    public EvaluateAvgBean getEvaluateAvg() {
        return evaluateAvg;
    }

    public void setEvaluateAvg(EvaluateAvgBean evaluateAvg) {
        this.evaluateAvg = evaluateAvg;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getExitState() {
        return exitState;
    }

    public void setExitState(int exitState) {
        this.exitState = exitState;
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

    public ProjectUserBean getCreateUser() {
        return createUser;
    }

    public void setCreateUser(ProjectUserBean createUser) {
        this.createUser = createUser;
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

    public String getProject_evaluates() {
        return project_evaluates;
    }

    public void setProject_evaluates(String project_evaluates) {
        this.project_evaluates = project_evaluates;
    }

    public String getProject_address() {
        return project_address;
    }

    public void setProject_address(String project_address) {
        this.project_address = project_address;
    }

    public String getProject_introducer() {
        return project_introducer;
    }

    public void setProject_introducer(String project_introducer) {
        this.project_introducer = project_introducer;
    }

    public String getProject_introducer_tel() {
        return project_introducer_tel;
    }

    public void setProject_introducer_tel(String project_introducer_tel) {
        this.project_introducer_tel = project_introducer_tel;
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

    public FileBean getVideo() {
        return video;
    }

    public void setVideo(FileBean video) {
        this.video = video;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }
}
