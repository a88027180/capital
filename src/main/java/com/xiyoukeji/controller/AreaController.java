package com.xiyoukeji.controller;

import com.xiyoukeji.beans.ProjectBean;
import com.xiyoukeji.beans.SearchCity;
import com.xiyoukeji.entity.Project;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.AreaService;
import com.xiyoukeji.service.FileService;
import com.xiyoukeji.service.UserService;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Core;
import com.xiyoukeji.utils.ErrCodeExcetion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/20.
 */
@Controller
public class AreaController {
    @Resource
    AreaService areaService;
    @Resource
    HttpSession session;
    @Resource
    UserService userService;


    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        if (runtimeException instanceof ErrCodeExcetion)
            return MapTool.Map().put("code", ((ErrCodeExcetion) runtimeException).getCode()).put("msg", runtimeException.getMessage());
        else
            return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*获取省列表*/
    @RequestMapping(value = "/getProvinceList")
    @ResponseBody
    public Map getProvinceList() {
        return MapTool.Mapok().put("provinces", areaService.getProvinceList());
    }

    /*获取市列表*/
    @RequestMapping(value = "/getCityList")
    @ResponseBody
    public Map getCityList(String provinceid) {
        return MapTool.Mapok().put("cities", areaService.getCityList(provinceid));
    }

    /*获取查询城市列表数据来源:项目列表*/
    @RequestMapping(value = "/getSearchCityList")
    @ResponseBody
    public Map getSearchCityList(int type, Integer foundationId) {
        Map map = null;
        if (userService.isLog()) {
            List projects = areaService.getSearchCityList(type, foundationId);
            map = MapTool.Map().put("list", projects);
        }
        return MapTool.Mapok().put("data", map);
    }
}
