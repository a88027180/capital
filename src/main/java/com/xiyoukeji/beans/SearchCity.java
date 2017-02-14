package com.xiyoukeji.beans;

import com.xiyoukeji.utils.AssignType;

/**
 * Created by dasiy on 17/2/9.
 */
@AssignType
public class SearchCity {
    private Integer id;
    private String city_name;

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
