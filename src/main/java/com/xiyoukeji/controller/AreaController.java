package com.xiyoukeji.controller;

import com.xiyoukeji.beans.ProjectBean;
import com.xiyoukeji.beans.SearchCity;
import com.xiyoukeji.entity.Project;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.AreaService;
import com.xiyoukeji.service.FileService;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Core;
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


    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
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
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            List<SearchCity> list = new ArrayList<>();
            List projects = areaService.getSearchCityList(type, foundationId);
//            for (int i = 0; i < projects.size(); i++) {
//                SearchCity searchCity = new SearchCity();
//                Core.assignDest(searchCity, projects.get(i));
//                list.add(searchCity);
//            }
            return MapTool.Mapok().put("data", MapTool.Map().put("list", projects));
        }
    }
}
