package com.xiyoukeji.beans;

import com.xiyoukeji.utils.AssignType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by dasiy on 16/12/20.
 */
@AssignType
public class CommentTabBean {
    private Integer id;
    private String name;
    private int type;
    private int state = 1;
    private int notice = 0;
    private String notice_text;

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNotice() {
        return notice;
    }

    public void setNotice(int notice) {
        this.notice = notice;
    }

    public String getNotice_text() {
        return notice_text;
    }

    public void setNotice_text(String notice_text) {
        this.notice_text = notice_text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
