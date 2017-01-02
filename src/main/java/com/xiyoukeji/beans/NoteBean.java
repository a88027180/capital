package com.xiyoukeji.beans;

import com.xiyoukeji.entity.User;
import com.xiyoukeji.utils.AssignType;

/**
 * Created by dasiy on 16/12/21.
 */
@AssignType
public class NoteBean {
    private Integer id;
    private CommentTabBean commentTab;
    private String content;
    private String date;
    private String address;
    private String member;
    private String object;
    private EvaluateUserBean user;
    private String create_time;

    public EvaluateUserBean getUser() {
        return user;
    }

    public void setUser(EvaluateUserBean user) {
        this.user = user;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public CommentTabBean getCommentTab() {
        return commentTab;
    }

    public void setCommentTab(CommentTabBean commentTab) {
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
