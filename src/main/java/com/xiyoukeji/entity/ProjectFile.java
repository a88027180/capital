package com.xiyoukeji.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by dasiy on 16/12/30.
 */
@Entity
@Table(name = "project_file")
@DynamicInsert
@DynamicUpdate
public class ProjectFile {
    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    @OneToOne
    private Project project;
    @OneToOne
    private File briefing;
    @OneToOne
    private File proposal;
    @OneToOne
    private File agreement;
    @OneToMany
    @JoinTable(name = "project_reports", joinColumns = {@JoinColumn(name = "projectFile_id")}, inverseJoinColumns = {@JoinColumn(name = "reports_id")})
    private List<File> reports;
    @OneToMany
    @JoinTable(name = "project_schedules", joinColumns = {@JoinColumn(name = "projectFile_id")}, inverseJoinColumns = {@JoinColumn(name = "schedules_id")})
    private List<File> schedules;
    @OneToMany
    @JoinTable(name = "project_manages", joinColumns = {@JoinColumn(name = "projectFile_id")}, inverseJoinColumns = {@JoinColumn(name = "manages_id")})
    private List<File> manages;
    @OneToMany
    @JoinTable(name = "project_profits", joinColumns = {@JoinColumn(name = "projectFile_id")}, inverseJoinColumns = {@JoinColumn(name = "profits_id")})
    private List<File> profits;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public File getBriefing() {
        return briefing;
    }

    public void setBriefing(File briefing) {
        this.briefing = briefing;
    }

    public File getProposal() {
        return proposal;
    }

    public void setProposal(File proposal) {
        this.proposal = proposal;
    }

    public File getAgreement() {
        return agreement;
    }

    public void setAgreement(File agreement) {
        this.agreement = agreement;
    }

    public List<File> getReports() {
        return reports;
    }

    public void setReports(List<File> reports) {
        this.reports = reports;
    }

    public List<File> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<File> schedules) {
        this.schedules = schedules;
    }

    public List<File> getManages() {
        return manages;
    }

    public void setManages(List<File> manages) {
        this.manages = manages;
    }

    public List<File> getProfits() {
        return profits;
    }

    public void setProfits(List<File> profits) {
        this.profits = profits;
    }
}
