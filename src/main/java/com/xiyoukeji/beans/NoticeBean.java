package com.xiyoukeji.beans;

import com.xiyoukeji.entity.Notice;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.utils.AssignType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasiy on 17/1/18.
 */
@AssignType
public class NoticeBean {
    private Integer id;
    private NoticeUserBean send_user;
    private List<NoticeUserBean> request_users = new ArrayList<>();
    private String title;
    private String content;
    private long publish_time;
    private long notice_time;
    private String type;
    private EvaluateProjectBean project;
    private List<FileBean> files = new ArrayList<>();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getNotice_time() {
        return notice_time;
    }

    public void setNotice_time(long notice_time) {
        this.notice_time = notice_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EvaluateProjectBean getProject() {
        return project;
    }

    public void setProject(EvaluateProjectBean project) {
        this.project = project;
    }

    public NoticeUserBean getSend_user() {
        return send_user;
    }

    public void setSend_user(NoticeUserBean send_user) {
        this.send_user = send_user;
    }

    public List<NoticeUserBean> getRequest_users() {
        return request_users;
    }

    public void setRequest_users(List<NoticeUserBean> request_users) {
        this.request_users = request_users;
    }

    public List<FileBean> getFiles() {
        return files;
    }

    public void setFiles(List<FileBean> files) {
        this.files = files;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(long publish_time) {
        this.publish_time = publish_time;
    }
}
