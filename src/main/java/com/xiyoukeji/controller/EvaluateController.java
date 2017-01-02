package com.xiyoukeji.controller;

import com.xiyoukeji.beans.EvaluateAvgBean;
import com.xiyoukeji.beans.EvaluateBean;
import com.xiyoukeji.beans.EvaluateRecordBean;
import com.xiyoukeji.beans.ProjectBean;
import com.xiyoukeji.entity.Evaluate;
import com.xiyoukeji.entity.EvaluateAvg;
import com.xiyoukeji.entity.EvaluateRecord;
import com.xiyoukeji.service.EvaluateService;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Core;
import org.hibernate.event.spi.EventSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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

    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }
//
//    /*用户获取评级-一条*/
//    @RequestMapping(value = "/getEvaluate")
//    @ResponseBody
//    public Map getEvaluate(Integer projectId, Integer userId) {
//        Evaluate evaluate = evaluateService.getEvaluate(projectId, userId);
//        EvaluateBean evaluateBean = new EvaluateBean();
//        Core.assignDest(evaluateBean, evaluate);
//        return MapTool.Mapok().put("data", MapTool.Map().put("evaluate", evaluateBean));
//    }

    /*用户获取评级列表*/
    @RequestMapping(value = "/getEvaluateList")
    @ResponseBody
    public Map getEvaluateList(Integer projectId, Integer userId, int number) {
        List<EvaluateBean> list = new ArrayList<>();
        List<Evaluate> evaluates = evaluateService.getEvaluateList(projectId, userId, number);
        for (int i = 0; i < evaluates.size(); i++) {
            EvaluateBean evaluateBean = new EvaluateBean();
            Core.assignDest(evaluateBean, evaluates.get(i));
            list.add(evaluateBean);
        }
        return MapTool.Mapok().put("data", MapTool.Map().put("list", list));
    }
//
//    /*获取项目评级平均值*/
//    @RequestMapping(value = "/getEvaluateAvg")
//    @ResponseBody
//    public Map getEvaluateAvg(Integer projectId) {
//        EvaluateAvg evaluateAvg = evaluateService.getEvaluateAvg(projectId);
//        EvaluateAvgBean evaluateAvgBean = new EvaluateAvgBean();
//        Core.assignDest(evaluateAvgBean, evaluateAvg);
//        return MapTool.Mapok().put("data", MapTool.Map().put("evaluateAvg", evaluateAvgBean));
//    }

    /*获取项目评级平均值列表*/
    @RequestMapping(value = "/getEvaluateAvgList")
    @ResponseBody
    public Map getEvaluateAvgList(Integer projectId, int number) {
        List<EvaluateAvgBean> list = new ArrayList<>();
        List<EvaluateAvg> evaluateAvgs = evaluateService.getEvaluateAvgList(projectId, number);
        for (int i = 0; i < evaluateAvgs.size(); i++) {
            EvaluateAvgBean evaluateAvgBean = new EvaluateAvgBean();
            Core.assignDest(evaluateAvgBean, evaluateAvgs.get(i));
            list.add(evaluateAvgBean);
        }
        return MapTool.Mapok().put("data", MapTool.Map().put("list", list));
    }

    /*获取主页项目评级5条*/
    @RequestMapping(value = "/getEvaluateRecordList")
    @ResponseBody
    public Map getEvaluateRecordList(int number) {
        List<EvaluateRecordBean> list = new ArrayList<>();
        List<EvaluateRecord> evaluateRecords = evaluateService.getEvaluateRecordList(number);
        for (int i = 0; i < evaluateRecords.size(); i++) {
            EvaluateRecordBean evaluateRecordBean = new EvaluateRecordBean();
            Core.assignDest(evaluateRecordBean, evaluateRecords.get(i));
            list.add(evaluateRecordBean);
        }
        return MapTool.Mapok().put("data", MapTool.Map().put("list", list));
    }

    /*新建项目评级*/
    @RequestMapping(value = "/saveorupdateEvaluate")
    @ResponseBody
    public Map saveorupdateEvaluate(Evaluate evaluate) {
        return MapTool.Mapok().put("data", MapTool.Map().put("projectId", evaluateService.saveorupdateEvaluate(evaluate)));
    }
}
