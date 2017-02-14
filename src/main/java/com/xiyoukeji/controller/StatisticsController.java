package com.xiyoukeji.controller;

import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.StatisticsService;
import com.xiyoukeji.service.VocationService;
import com.xiyoukeji.tools.MapTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by dasiy on 16/12/20.
 */
@Controller
public class StatisticsController {
    @Resource
    StatisticsService statisticsService;
    @Resource
    HttpSession session;


    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*已投资项目信息*/
    @RequestMapping(value = "/getType0")
    @ResponseBody
    public Map getType0() {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else
            return MapTool.Mapok().put("data", statisticsService.getType0());
    }

    /*已退出投资项目信息*/
    @RequestMapping(value = "/getType1")
    @ResponseBody
    public Map getType1() {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else
            return MapTool.Mapok().put("data", statisticsService.getType1());
    }

    /*未投项目统计*/
    @RequestMapping(value = "/getType2")
    @ResponseBody
    public Map getType2() {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else
            return MapTool.Mapok().put("data", statisticsService.getType2());
    }

    /*新增项目统计*/
    @RequestMapping(value = "/getType3")
    @ResponseBody
    public Map getType3() {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else
            return MapTool.Mapok().put("data", statisticsService.getType3());
    }

}
