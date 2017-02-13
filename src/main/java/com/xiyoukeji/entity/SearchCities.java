package com.xiyoukeji.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by dasiy on 17/2/13.
 */
@Entity
@Table(name = "search_cities")
@DynamicInsert
@DynamicUpdate
public class SearchCities {
    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
