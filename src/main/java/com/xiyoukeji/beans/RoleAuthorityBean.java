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
    private int file_one;
    private int file_two;
    private int file_three;
    private int file_four;

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

    public int getFile_one() {
        return file_one;
    }

    public void setFile_one(int file_one) {
        this.file_one = file_one;
    }

    public int getFile_two() {
        return file_two;
    }

    public void setFile_two(int file_two) {
        this.file_two = file_two;
    }

    public int getFile_three() {
        return file_three;
    }

    public void setFile_three(int file_three) {
        this.file_three = file_three;
    }

    public int getFile_four() {
        return file_four;
    }

    public void setFile_four(int file_four) {
        this.file_four = file_four;
    }
}
