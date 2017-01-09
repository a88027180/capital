package com.xiyoukeji.beans;

import com.xiyoukeji.utils.AssignType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by dasiy on 16/12/21.
 */
@AssignType
public class FileBean {
    private Integer id;
    private String fileName;
    private String url;
    private long update_current;

    public long getUpdate_current() {
        return update_current;
    }

    public void setUpdate_current(long update_current) {
        this.update_current = update_current;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
