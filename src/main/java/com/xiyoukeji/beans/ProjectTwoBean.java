package com.xiyoukeji.beans;

import com.xiyoukeji.utils.AssignType;

/**
 * Created by dasiy on 17/3/3.
 */
@AssignType
public class ProjectTwoBean {
    private Integer project_id;
    private String project_name;
    private String province_name;
    private String city_name;
    private String project_resource;
    private String project_stage;
    private String user_name;
    private String foundation_name;
    private Integer item_all;
    private Integer project_schedule;

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getProject_resource() {
        return project_resource;
    }

    public void setProject_resource(String project_resource) {
        this.project_resource = project_resource;
    }

    public String getProject_stage() {
        return project_stage;
    }

    public void setProject_stage(String project_stage) {
        this.project_stage = project_stage;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getFoundation_name() {
        return foundation_name;
    }

    public void setFoundation_name(String foundation_name) {
        this.foundation_name = foundation_name;
    }

    public Integer getItem_all() {
        return item_all;
    }

    public void setItem_all(Integer item_all) {
        this.item_all = item_all;
    }

    public Integer getProject_schedule() {
        return project_schedule;
    }

    public void setProject_schedule(Integer project_schedule) {
        this.project_schedule = project_schedule;
    }
}
