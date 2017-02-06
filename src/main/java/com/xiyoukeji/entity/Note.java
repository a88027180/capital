package com.xiyoukeji.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by dasiy on 16/12/21.
 */
@Entity
@Table(name = "note")
@DynamicInsert
@DynamicUpdate
public class Note {
    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    @ManyToOne
    private User user;
    private long create_time;
    @OneToOne
    private CommentTab commentTab;
    @ManyToOne
    @JoinTable(name = "project_note", joinColumns = {@JoinColumn(name = "note_id")}, inverseJoinColumns = {@JoinColumn(name = "project_id")})
    private Project project;
    private String content;
    private String date;
    private String address;
    private String member;
    private String object;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public CommentTab getCommentTab() {
        return commentTab;
    }

    public void setCommentTab(CommentTab commentTab) {
        this.commentTab = commentTab;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}
