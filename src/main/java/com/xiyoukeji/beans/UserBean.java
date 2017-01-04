package com.xiyoukeji.beans;

import com.xiyoukeji.entity.File;
import com.xiyoukeji.entity.Role;
import com.xiyoukeji.utils.AssignType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasiy on 16/12/20.
 */
@AssignType
public class UserBean {
    private Integer id;
    private FileBean photo;
    private String userName;
    private String password;
    private UserRoleBean role;
    private String name;
    private String position;
    private String address;
    private String phone;
    private int available;
    private int isBand;
    private List<UserFoundationBean> list_foundation = new ArrayList<>();
    private List<UserProjectBean> list_project = new ArrayList<>();

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

    public UserRoleBean getRole() {
        return role;
    }

    public void setRole(UserRoleBean role) {
        this.role = role;
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

//    public List<Foundation> getList_foundation() {
//        return list_foundation;
//    }
//
//    public void setList_foundation(List<Foundation> list_foundation) {
//        this.list_foundation = list_foundation;
//    }
//
//
//    public List<Project> getList_project() {
//        return list_project;
//    }
//
//    public void setList_project(List<Project> list_project) {
//        this.list_project = list_project;
//    }


    public FileBean getPhoto() {
        return photo;
    }

    public void setPhoto(FileBean photo) {
        this.photo = photo;
    }

    public List<UserFoundationBean> getList_foundation() {
        return list_foundation;
    }

    public void setList_foundation(List<UserFoundationBean> list_foundation) {
        this.list_foundation = list_foundation;
    }

    public List<UserProjectBean> getList_project() {
        return list_project;
    }

    public void setList_project(List<UserProjectBean> list_project) {
        this.list_project = list_project;
    }
}
