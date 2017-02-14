package com.xiyoukeji.beans;

import com.xiyoukeji.entity.VocationTwo;
import com.xiyoukeji.utils.AssignType;

/**
 * Created by dasiy on 17/2/14.
 */
@AssignType
public class VocationThreeBean {
    private Integer id;
    private String name;
    private Integer type = 0;
    private VocationTwoBean vocationTwo;

    public VocationTwoBean getVocationTwo() {
        return vocationTwo;
    }

    public void setVocationTwo(VocationTwoBean vocationTwo) {
        this.vocationTwo = vocationTwo;
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
