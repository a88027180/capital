package com.xiyoukeji.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dasiy on 16/12/19.
 */
@Controller
public class MainController {
    @RequestMapping(value = "/test")
    @ResponseBody
    public Map index(){
        Map<String,Object> map=new HashMap<>();
        map.put("x","y");
        return map;
    }
}
