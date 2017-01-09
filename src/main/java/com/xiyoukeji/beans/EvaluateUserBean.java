package com.xiyoukeji.beans;

import com.xiyoukeji.utils.AssignType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasiy on 16/12/20.
 */
@AssignType
public class EvaluateUserBean {
    private Integer id;
    private FileBean photo;
    private String userName;
    private String password;
    private String name;
    private String position;
    private String address;
    private String phone;
    private String remark;
    private int available;
    private int isBand;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public FileBean getPhoto() {
        return photo;
    }

    public void setPhoto(FileBean photo) {
        this.photo = photo;
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

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
