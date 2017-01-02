package com.xiyoukeji.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dasiy on 16/12/20.
 */
@Entity
@Table(name = "foundation")
@DynamicInsert
@DynamicUpdate
public class Foundation {
    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    @Column(length = 50, unique = true)
    private String name;
    private String responsibility;
    @ManyToMany
    @JoinTable(name = "user_foundation", joinColumns = {@JoinColumn(name = "foundation_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    List<User> list_user;
    @OneToMany
    @JoinTable(name = "project_foundation", joinColumns = {@JoinColumn(name = "foundation_id")}, inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private List<Project> list_project;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public List<Project> getList_project() {
        return list_project;
    }

    public void setList_project(List<Project> list_project) {
        this.list_project = list_project;
    }

    public List<User> getList_user() {
        return list_user;
    }

    public void setList_user(List<User> list_user) {
        this.list_user = list_user;
    }
}
