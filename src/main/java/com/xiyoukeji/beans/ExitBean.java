package com.xiyoukeji.beans;

import com.xiyoukeji.utils.AssignType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasiy on 17/2/14.
 */
@AssignType
public class ExitBean {
    private List<Statistics> list = new ArrayList<>();
    private long month;
    private long year;

    public List<Statistics> getList() {
        return list;
    }

    public void setList(List<Statistics> list) {
        this.list = list;
    }

    public long getMonth() {
        return month;
    }

    public void setMonth(long month) {
        this.month = month;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }
}
