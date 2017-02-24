package com.xiyoukeji.beans;

import com.xiyoukeji.utils.AssignType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasiy on 16/12/20.
 */
@AssignType
public class MeetingBean {
    private Integer id;
    private List<ProjectBean> projects = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public List<ProjectBean> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectBean> projects) {
        this.projects = projects;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
