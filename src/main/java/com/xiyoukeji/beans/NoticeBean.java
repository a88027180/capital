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
    private String Content;
    private long publish_time;
    private EvaluateProjectBean project;
    private List<FileBean> files = new ArrayList<>();

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
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public long getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(long publish_time) {
        this.publish_time = publish_time;
    }
}
