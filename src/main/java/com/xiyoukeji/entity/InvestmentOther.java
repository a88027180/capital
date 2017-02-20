package com.xiyoukeji.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by dasiy on 17/1/8.
 */
@Entity
@Table(name = "investment_other")
@DynamicInsert
@DynamicUpdate
public class InvestmentOther {
    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    @ManyToOne
    @JoinTable(name = "project_InvestmentOther", joinColumns = {@JoinColumn(name = "investmentOther_id")}, inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Project project;
    private String other;
    private String investment_other;
    private String share_other;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
