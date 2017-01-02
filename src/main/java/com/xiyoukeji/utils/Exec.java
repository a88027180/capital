package com.xiyoukeji.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangqiyun on 16/9/24.
 */

/**
 * @deprecated
 */
public class Exec {
    // TODO: 16/9/26
    public static <T> Map out(T object, Class<T> c, Set<String> add,Set<String> remove,String base){
        if(object==null)return null;
        Map result=new HashMap();
        for(Field field:c.getDeclaredFields()){

        }
        return null;
    }
}
