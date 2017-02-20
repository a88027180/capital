package com.xiyoukeji.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dasiy on 17/1/18.
 */
@Entity
@Table(name = "notice")
@DynamicInsert
@DynamicUpdate
public class Notice {
    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    @OneToOne
    private User send_user;
    @ManyToMany
    @JoinTable(name = "user_notice", joinColumns = {@JoinColumn(name = "notice_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> request_users;
    private String title;
    private String content;
    private long publish_time;
    private long notice_time;
    @ManyToOne
    @JoinTable(name = "project_notice", joinColumns = {@JoinColumn(name = "notice_id")}, inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Project project;
    @OneToMany
    private List<File> files;
    private String type;

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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getSend_user() {
        return send_user;
    }

    public void setSend_user(User send_user) {
        this.send_user = send_user;
    }

    public List<User> getRequest_users() {
        return request_users;
    }

    public void setRequest_users(List<User> request_users) {
        this.request_users = request_users;
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
