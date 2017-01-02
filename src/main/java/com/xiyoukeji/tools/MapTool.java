package com.xiyoukeji.tools;

import java.util.HashMap;

/**
 * Created by wangqiyun on 16/5/23.
 */
public class MapTool extends HashMap<String, Object> {
    @Override
    public MapTool put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static MapTool Map() {
        return new MapTool();
    }

    public static MapTool Mapok() {
        MapTool mapTool = new MapTool();
        mapTool.put("code", "0");
        return mapTool;
    }
}
