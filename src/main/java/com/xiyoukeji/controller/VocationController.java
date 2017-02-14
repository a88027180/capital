package com.xiyoukeji.controller;

import com.xiyoukeji.beans.SearchCity;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.AreaService;
import com.xiyoukeji.service.VocationService;
import com.xiyoukeji.tools.MapTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/20.
 */
@Controller
public class VocationController {
    @Resource
    VocationService vocationService;
    @Resource
    HttpSession session;


    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

//    /*获取省列表*/
//    @RequestMapping(value = "/saveorupdate_vocationOne")
//    @ResponseBody
//    public Map saveorupdate_vocationOne() {
//        return MapTool.Mapok().put("provinces", areaService.getProvinceList());
//    }


}
