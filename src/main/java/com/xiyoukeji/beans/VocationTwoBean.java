package com.xiyoukeji.beans;

import com.xiyoukeji.entity.VocationOne;
import com.xiyoukeji.utils.AssignType;

/**
 * Created by dasiy on 17/2/14.
 */
@AssignType
public class VocationTwoBean {
    private Integer id;
    private String name;
    private Integer type = 0;
    private VocationOneBean vocationOne;

    public VocationOneBean getVocationOne() {
        return vocationOne;
    }

    public void setVocationOne(VocationOneBean vocationOne) {
        this.vocationOne = vocationOne;
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
