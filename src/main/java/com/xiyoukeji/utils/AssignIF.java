package com.xiyoukeji.utils;

/**
 * Created by wangqiyun on 16/8/13.
 */
/**
 * @deprecated
 */
public class AssignIF {
    private String value;
    private boolean isNull;
    public AssignIF(String value,boolean isNull){
        if("".equals(value))
            this.value=null;
        else
            this.value=value;
        this.isNull=isNull;
    }
    public String value(){
        return value;
    }
    public boolean isNull(){
        return isNull;
    }
}
