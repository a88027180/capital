package com.xiyoukeji.beans;

import com.xiyoukeji.utils.AssignType;

/**
 * Created by dasiy on 16/12/22.
 */
@AssignType
public class VocationThreeBean {
    private Integer id;
    private String name;
    private Integer type;
    private VocationTwoBean vocationTwoBean;

    public VocationTwoBean getVocationTwoBean() {
        return vocationTwoBean;
    }

    public void setVocationTwoBean(VocationTwoBean vocationTwoBean) {
        this.vocationTwoBean = vocationTwoBean;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
