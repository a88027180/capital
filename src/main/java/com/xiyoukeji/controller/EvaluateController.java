package com.xiyoukeji.controller;

import com.google.gson.Gson;
import com.xiyoukeji.beans.EvaluateAvgBean;
import com.xiyoukeji.beans.EvaluateBean;
import com.xiyoukeji.beans.EvaluateRecordBean;
import com.xiyoukeji.beans.ProjectBean;
import com.xiyoukeji.entity.*;
import com.xiyoukeji.service.EvaluateService;
import com.xiyoukeji.service.UserService;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Core;
import com.xiyoukeji.utils.ErrCodeExcetion;
import org.hibernate.event.spi.EventSource;
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
 * Created by dasiy on 16/12/23.
 */
@Controller
public class EvaluateController {
    @Resource
    EvaluateService evaluateService;
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

    /*用户获取评级列表*/
    @RequestMapping(value = "/getEvaluateList")
    @ResponseBody
    public Map getEvaluateList(Integer projectId, Integer userId, int number) {
        Map map = null;
        if (userService.isLog()) {
            List<EvaluateBean> list = new ArrayList<>();
            List<Evaluate> evaluates = evaluateService.getEvaluateList(projectId, userId, number);
            for (int i = 0; i < evaluates.size(); i++) {
                EvaluateBean evaluateBean = new EvaluateBean();
                Core.assignDest(evaluateBean, evaluates.get(i));
                list.add(evaluateBean);
            }
            map = MapTool.Mapok().put("data", MapTool.Map().put("list", list));
        }
        return map;
    }

    /*获取项目评级平均值列表*/
    @RequestMapping(value = "/getEvaluateAvgList")
    @ResponseBody
    public Map getEvaluateAvgList(Integer projectId, int number) {
        Map map = null;
        if (userService.isLog()) {
            List<EvaluateAvgBean> list = new ArrayList<>();
            List<EvaluateAvg> evaluateAvgs = evaluateService.getEvaluateAvgList(projectId, number);
            for (int i = 0; i < evaluateAvgs.size(); i++) {
                EvaluateAvgBean evaluateAvgBean = new EvaluateAvgBean();
                Core.assignDest(evaluateAvgBean, evaluateAvgs.get(i));
                evaluateAvgBean.setItem_all(evaluateAvgs.get(i).getItem_all() / evaluateAvgs.get(i).getNumber());
                evaluateAvgBean.setItem_one(evaluateAvgs.get(i).getItem_one() / evaluateAvgs.get(i).getNumber());
                evaluateAvgBean.setItem_two(evaluateAvgs.get(i).getItem_two() / evaluateAvgs.get(i).getNumber());
                evaluateAvgBean.setItem_three(evaluateAvgs.get(i).getItem_three() / evaluateAvgs.get(i).getNumber());
                evaluateAvgBean.setItem_four(evaluateAvgs.get(i).getItem_four() / evaluateAvgs.get(i).getNumber());
                evaluateAvgBean.setItem_five(evaluateAvgs.get(i).getItem_five() / evaluateAvgs.get(i).getNumber());
                evaluateAvgBean.setItem_six(evaluateAvgs.get(i).getItem_six() / evaluateAvgs.get(i).getNumber());
                evaluateAvgBean.setItem_seven(evaluateAvgs.get(i).getItem_seven() / evaluateAvgs.get(i).getNumber());
                evaluateAvgBean.setItem_eight(evaluateAvgs.get(i).getItem_eight() / evaluateAvgs.get(i).getNumber());
                evaluateAvgBean.setItem_nine(evaluateAvgs.get(i).getItem_nine() / evaluateAvgs.get(i).getNumber());
                evaluateAvgBean.setItem_ten(evaluateAvgs.get(i).getItem_ten() / evaluateAvgs.get(i).getNumber());
                list.add(evaluateAvgBean);
            }
            map = MapTool.Mapok().put("data", MapTool.Map().put("list", list));
        }
        return map;
    }

    /*获取主页项目评级5条*/
    @RequestMapping(value = "/getEvaluateRecordList")
    @ResponseBody
    public Map getEvaluateRecordList(Integer projectId, Integer userId, int number) {
        Map map = null;
        if (userService.isLog()) {
            List<EvaluateRecordBean> list = new ArrayList<>();
            List<EvaluateRecord> evaluateRecords = evaluateService.getEvaluateRecordList(projectId, userId, number);
            for (int i = 0; i < evaluateRecords.size(); i++) {
                EvaluateRecordBean evaluateRecordBean = new EvaluateRecordBean();
                Core.assignDest(evaluateRecordBean, evaluateRecords.get(i));
                list.add(evaluateRecordBean);
            }
            map = MapTool.Mapok().put("data", MapTool.Map().put("list", list));
        }
        return map;

    }

    /*新建项目评级*/
    @RequestMapping(value = "/saveorupdateEvaluate")
    @ResponseBody
    public Map saveorupdateEvaluate(String strEvaluate) {
        Evaluate evaluate = new Gson().fromJson(strEvaluate, Evaluate.class);
        Map map = null;
        if (userService.isLog()) {
            evaluate.setUser((User) session.getAttribute("user"));
            map = MapTool.Map().put("projectId", evaluateService.saveorupdateEvaluate(evaluate));
        }
        return MapTool.Mapok().put("data", map);

    }
}
