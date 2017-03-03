package com.xiyoukeji.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasiy on 16/12/22.
 */
@Entity
@Table(name = "vocation")
@DynamicInsert
@DynamicUpdate
public class Vocation {
    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    private String name;
    private int type;
    private int parent_id = 0;
    private int double_parent_id = 0;
    private int available = 1;

    public Integer getDouble_parent_id() {
        return double_parent_id;
    }

    public void setDouble_parent_id(Integer double_parent_id) {
        this.double_parent_id = double_parent_id;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
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

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }
}
