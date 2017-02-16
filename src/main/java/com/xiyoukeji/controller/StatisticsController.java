package com.xiyoukeji.controller;

import com.xiyoukeji.beans.SearchStatistic;
import com.xiyoukeji.entity.Foundation;
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
import java.util.List;
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


    /*获取查询列表*/
    @RequestMapping(value = "/getSearchList")
    @ResponseBody
    public Map getSearchList() {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else
            return statisticsService.getSearchList();
    }

    /*标签与项目数量的关系*/
    @RequestMapping(value = "/vocation_project")
    @ResponseBody
    public Map vocation_project(SearchStatistic searchStatistic) {
        if (searchStatistic == null) {
            searchStatistic = new SearchStatistic();
        }
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() == 1) {
            /*外部角色*/
            StringBuffer buffer = new StringBuffer();
            List<Foundation> list = user1.getList_foundation();
            for (int i = 0; i < list.size(); i++) {
                buffer.append(list.get(i).getId());
                if (i != list.size() - 1) {
                    buffer.append(",");
                }
            }
            searchStatistic.setFoundationList(buffer.toString());
            return statisticsService.vocation_project(searchStatistic);

        } else {
            return statisticsService.vocation_project(searchStatistic);
        }

    }

    /*地区与项目数量的关系*/
    @RequestMapping(value = "/statistics_project")
    @ResponseBody
    public Map statistics_project(int type, SearchStatistic searchStatistic) {
        if (searchStatistic == null) {
            searchStatistic = new SearchStatistic();
        }
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() == 1) {
            /*外部角色*/
            StringBuffer buffer = new StringBuffer();
            List<Foundation> list = user1.getList_foundation();
            for (int i = 0; i < list.size(); i++) {
                buffer.append(list.get(i).getId());
                if (i != list.size() - 1) {
                    buffer.append(",");
                }
            }
            searchStatistic.setFoundationList(buffer.toString());
            return statisticsService.statistics_project(type, searchStatistic);

        } else {
            return statisticsService.statistics_project(type, searchStatistic);
        }
    }

}
