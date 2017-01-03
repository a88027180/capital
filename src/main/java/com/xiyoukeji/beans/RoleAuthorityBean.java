package com.xiyoukeji.beans;

import com.xiyoukeji.utils.AssignType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasiy on 16/12/22.
 */
@AssignType
public class RoleAuthorityBean {
    private Integer id;
    private int project_create;
    private int project_edit;
    private List<CommentTabBean> project_comments = new ArrayList<>();

    private int project_grade;
    private int notice_publish;
    private int file_report;
    private int file_schedule;
    private int file_manage;
    private int file_profit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getProject_create() {
        return project_create;
    }

    public void setProject_create(int project_create) {
        this.project_create = project_create;
    }

    public int getProject_edit() {
        return project_edit;
    }

    public void setProject_edit(int project_edit) {
        this.project_edit = project_edit;
    }

    public List<CommentTabBean> getProject_comments() {
        return project_comments;
    }

    public void setProject_comments(List<CommentTabBean> project_comments) {
        this.project_comments = project_comments;
    }

    public int getProject_grade() {
        return project_grade;
    }

    public void setProject_grade(int project_grade) {
        this.project_grade = project_grade;
    }

    public int getNotice_publish() {
        return notice_publish;
    }

    public void setNotice_publish(int notice_publish) {
        this.notice_publish = notice_publish;
    }

    public int getFile_report() {
        return file_report;
    }

    public void setFile_report(int file_report) {
        this.file_report = file_report;
    }

    public int getFile_schedule() {
        return file_schedule;
    }

    public void setFile_schedule(int file_schedule) {
        this.file_schedule = file_schedule;
    }

    public int getFile_manage() {
        return file_manage;
    }

    public void setFile_manage(int file_manage) {
        this.file_manage = file_manage;
    }

    public int getFile_profit() {
        return file_profit;
    }

    public void setFile_profit(int file_profit) {
        this.file_profit = file_profit;
    }
}
