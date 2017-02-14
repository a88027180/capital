package com.xiyoukeji.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by dasiy on 16/12/22.
 */
@Entity
@Table(name = "vocation_two")
@DynamicInsert
@DynamicUpdate
public class VocationTwo {
    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    private String name;
    @ManyToOne
    private VocationOne vocationOne;
    private Integer type = 1;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public VocationOne getVocationOne() {
        return vocationOne;
    }

    public void setVocationOne(VocationOne vocationOne) {
        this.vocationOne = vocationOne;
    }
}
