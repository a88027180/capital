package com.xiyoukeji.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by dasiy on 16/12/22.
 */
@Entity
@Table(name = "vocation_three")
@DynamicInsert
@DynamicUpdate
public class VocationThree {
    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    private String name;
    @ManyToOne
    private VocationTwo vocationTwo;

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

    public VocationTwo getVocationTwo() {
        return vocationTwo;
    }

    public void setVocationTwo(VocationTwo vocationTwo) {
        this.vocationTwo = vocationTwo;
    }
}
