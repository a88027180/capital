package com.xiyoukeji.beans;

import com.xiyoukeji.entity.File;
import com.xiyoukeji.entity.Project;
import com.xiyoukeji.utils.AssignType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasiy on 16/12/30.
 */
@AssignType
public class ProjectFileBean {
    private Integer id;
    private EvaluateProjectBean project;
    private FileBean briefing;
    private FileBean proposal;
    private FileBean agreement;
    private List<FileBean> reports=new ArrayList<>();
    private List<FileBean> schedules=new ArrayList<>();
    private List<FileBean> manages=new ArrayList<>();
    private List<FileBean> profits=new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EvaluateProjectBean getProject() {
        return project;
    }

    public void setProject(EvaluateProjectBean project) {
        this.project = project;
    }

    public FileBean getBriefing() {
        return briefing;
    }

    public void setBriefing(FileBean briefing) {
        this.briefing = briefing;
    }

    public FileBean getProposal() {
        return proposal;
    }

    public void setProposal(FileBean proposal) {
        this.proposal = proposal;
    }

    public FileBean getAgreement() {
        return agreement;
    }

    public void setAgreement(FileBean agreement) {
        this.agreement = agreement;
    }

    public List<FileBean> getReports() {
        return reports;
    }

    public void setReports(List<FileBean> reports) {
        this.reports = reports;
    }

    public List<FileBean> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<FileBean> schedules) {
        this.schedules = schedules;
    }

    public List<FileBean> getManages() {
        return manages;
    }

    public void setManages(List<FileBean> manages) {
        this.manages = manages;
    }

    public List<FileBean> getProfits() {
        return profits;
    }

    public void setProfits(List<FileBean> profits) {
        this.profits = profits;
    }
}
