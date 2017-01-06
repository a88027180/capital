package com.xiyoukeji.beans;

import com.xiyoukeji.entity.Project;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.utils.AssignType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.mapping.Array;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasiy on 16/12/20.
 */
@AssignType
public class FoundationBean {
    private Integer id;
    private String name;
    private String money;
    private String responsibility;
    List<FoundationUserBean> list_user = new ArrayList<>();
    private List<FoundationProjectBean> list_project = new ArrayList<>();

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

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

    public List<FoundationUserBean> getList_user() {
        return list_user;
    }

    public void setList_user(List<FoundationUserBean> list_user) {
        this.list_user = list_user;
    }

    public List<FoundationProjectBean> getList_project() {
        return list_project;
    }

    public void setList_project(List<FoundationProjectBean> list_project) {
        this.list_project = list_project;
    }
}
