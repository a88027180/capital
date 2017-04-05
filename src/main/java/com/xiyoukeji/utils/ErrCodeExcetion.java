package com.xiyoukeji.utils;

/**
 * Created by dasiy on 17/4/5.
 */
public class ErrCodeExcetion extends RuntimeException {
    private String code;

    public ErrCodeExcetion(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
