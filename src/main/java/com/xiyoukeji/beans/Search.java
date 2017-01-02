package com.xiyoukeji.beans;

import com.xiyoukeji.utils.AssignType;

/**
 * Created by dasiy on 16/12/24.
 */
@AssignType
public class Search {
    private Integer projectId;
//    private Integer search_type;//如果projectId没有 type必填:0草稿1发布(未投)2已投(有所属基金,foundationId必填)3退出
    private Integer foundationId;
    private String nameorcode;
    private String begin_time;
    private String end_time;
    private Integer schedule;
    private Integer project_type;
    private String address;
    private String resource;
    private String responsibility;
    private int page;
    private int line;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getFoundationId() {
        return foundationId;
    }

    public void setFoundationId(Integer foundationId) {
        this.foundationId = foundationId;
    }

    public String getNameorcode() {
        return nameorcode;
    }

    public void setNameorcode(String nameorcode) {
        this.nameorcode = nameorcode;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Integer getSchedule() {
        return schedule;
    }

    public void setSchedule(Integer schedule) {
        this.schedule = schedule;
    }

//    public Integer getSearch_type() {
//        return search_type;
//    }
//
//    public void setSearch_type(Integer search_type) {
//        this.search_type = search_type;
//    }

    public Integer getProject_type() {
        return project_type;
    }

    public void setProject_type(Integer project_type) {
        this.project_type = project_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}
