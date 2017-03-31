package com.xiyoukeji.beans;

import com.xiyoukeji.entity.Foundation;
import com.xiyoukeji.entity.Project;
import com.xiyoukeji.entity.Role;
import com.xiyoukeji.utils.AssignType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasiy on 16/12/20.
 */
@AssignType
public class FoundationUserBean {
    private Integer id;
    private String userName;
//    private RoleBean role;
    private String name;
    private String position;
    private String address;
    private String phone;
    private String remark;
    private int isBand;
    private int available;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//
//    public RoleBean getRole() {
//        return role;
//    }
//
//    public void setRole(RoleBean role) {
//        this.role = role;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIsBand() {
        return isBand;
    }

    public void setIsBand(int isBand) {
        this.isBand = isBand;
    }
}
