package com.xiyoukeji.controller;

import com.google.gson.Gson;
import com.xiyoukeji.beans.SearchCity;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.entity.Vocation;
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

    /*获取一级标签列表*/
    @RequestMapping(value = "/vocationOneList")
    @ResponseBody
    public Map vocationOneList() {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            return MapTool.Mapok().put("vocationOneList", vocationService.vocationOneList());
        }
    }

    /*获取二级标签列表*/
    @RequestMapping(value = "/vocationTwoList")
    @ResponseBody
    public Map vocationOneList(Integer vocationOneId) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            return MapTool.Mapok().put("vocationTwoList", vocationService.vocationTwoList(vocationOneId));
        }
    }

    /*获取三级标签列表*/
    @RequestMapping(value = "/vocationThreeList")
    @ResponseBody
    public Map vocationThreeList(Integer vocationTwoId) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            return MapTool.Mapok().put("vocationThreeList", vocationService.vocationThreeList(vocationTwoId));
        }
    }

    /*增加标签*/
    @RequestMapping(value = "/saveorupdateVocation")
    @ResponseBody

    public Map saveorupdateVocation(Vocation vocation) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() != 2) {
            return MapTool.Map().put("code", 3);
        } else {
            return vocationService.saveorupdateVocation(vocation);
        }
    }

    /*shanchu标签*/
    @RequestMapping(value = "/deleteVocation")
    @ResponseBody

    public Map saveorupdateVocation(Integer vocationId) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() != 2) {
            return MapTool.Map().put("code", 3);
        } else {
            return vocationService.deleteVocation(vocationId);
        }
    }
}
