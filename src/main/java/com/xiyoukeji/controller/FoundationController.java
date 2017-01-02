package com.xiyoukeji.controller;

import com.xiyoukeji.beans.FoundationBean;
import com.xiyoukeji.entity.Foundation;
import com.xiyoukeji.service.FoundationService;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Core;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/22.
 */
@Controller
public class FoundationController {
    @Resource
    FoundationService foundationService;

    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*创建或编辑基金*/
    @RequestMapping(value = "/saveorupdateFoundation")
    @ResponseBody
    public Map saveorupdate(Foundation foundation) {
        return MapTool.Mapok().put("data", MapTool.Map().put("foundationId", foundationService.saveorupdateFoundation(foundation)));
    }

    /*获取基金列表*/
    @RequestMapping(value = "/getFoundationList")
    @ResponseBody
    public Map getFoundationList() {

        List<FoundationBean> list = new ArrayList<>();
        List<Foundation> foundations = foundationService.getFoundationList();
        for (int i = 0; i < foundations.size(); i++) {
            FoundationBean foundationBean = new FoundationBean();
            Core.assignDest(foundationBean, foundations.get(i));
            list.add(foundationBean);
        }
        return MapTool.Mapok().put("data", MapTool.Map().put("list", list));
    }

    /*获取基金信息*/
    @RequestMapping(value = "/getFoundation")
    @ResponseBody
    public Map getFoundation(Integer id) {
        Foundation foundation = foundationService.getFoundation(id);
        FoundationBean foundationBean = new FoundationBean();
        Core.assignDest(foundationBean, foundation);
        return MapTool.Mapok().put("data", MapTool.Map().put("foundation", foundationBean));
    }

    /*增加或删除用户*/
    @RequestMapping(value = "/addordeleteFoundationUser")
    @ResponseBody
    public Map addordeleteFoundationUser(int type, Integer foundationId, Integer userId) {
        return MapTool.Mapok().put("data", MapTool.Map().put("foundationId", foundationService.addordeleteFoundationUser(type, foundationId, userId)));
    }

    /*增加或删除项目*/
    @RequestMapping(value = "/addordeleteFoundationProject")
    @ResponseBody
    public Map addordeleteFoundationProject(int type, Integer foundationId, Integer projectId) {
        return MapTool.Mapok().put("data", MapTool.Map().put("foundationId", foundationService.addordeleteFoundationProject(type, foundationId, projectId)));
    }
}
