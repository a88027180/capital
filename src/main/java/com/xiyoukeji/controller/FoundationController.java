package com.xiyoukeji.controller;

import com.xiyoukeji.beans.FoundationBean;
import com.xiyoukeji.entity.Foundation;
import com.xiyoukeji.entity.Project;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.FoundationService;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Core;
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
 * Created by dasiy on 16/12/22.
 */
@Controller
public class FoundationController {
    @Resource
    FoundationService foundationService;
    @Resource
    HttpSession session;


    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*创建或编辑基金 管理员权限*/
    @RequestMapping(value = "/saveorupdateFoundation")
    @ResponseBody
    public Map saveorupdate(Foundation foundation) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() != 2) {
            return MapTool.Map().put("code", 3);
        } else {
            return MapTool.Mapok().put("data", MapTool.Map().put("foundationId", foundationService.saveorupdateFoundation(foundation)));
        }
    }

    /*获取基金列表*/
    @RequestMapping(value = "/getFoundationList")
    @ResponseBody
    public Map getFoundationList() {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            List<FoundationBean> list = new ArrayList<>();
            List<Foundation> foundations = foundationService.getFoundationList();
            for (int i = 0; i < foundations.size(); i++) {
                FoundationBean foundationBean = new FoundationBean();
                Foundation foundation = new Foundation();
                foundation.setId(foundations.get(i).getId());
                foundation.setName(foundations.get(i).getName());
                foundation.setMoney(foundations.get(i).getMoney());
                foundation.setList_user(foundations.get(i).getList_user());
                foundation.setResponsibility(foundations.get(i).getResponsibility());
                List<Project> list1 = new ArrayList<>();
                for (int j = 0; j < foundations.get(i).getList_project().size(); j++) {
                    if (foundations.get(i).getList_project().get(j).getState() == 2) {
                        list1.add(foundations.get(i).getList_project().get(j));
                    }
                }
                foundation.setList_project(list1);
                Core.assignDest(foundationBean, foundation);
                list.add(foundationBean);
            }
            return MapTool.Mapok().put("data", MapTool.Map().put("list", list));
        }
    }

    /*获取基金信息*/
    @RequestMapping(value = "/getFoundation")
    @ResponseBody
    public Map getFoundation(Integer id) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            Foundation foundation = foundationService.getFoundation(id);
            FoundationBean foundationBean = new FoundationBean();
            Core.assignDest(foundationBean, foundation);
            return MapTool.Mapok().put("data", MapTool.Map().put("foundation", foundationBean));
        }
    }

    /*增加或删除用户 管理员权限*/
    @RequestMapping(value = "/addordeleteFoundationUser")
    @ResponseBody
    public Map addordeleteFoundationUser(int type, Integer foundationId, Integer userId) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() != 2) {
            return MapTool.Map().put("code", 3);
        } else {
            return MapTool.Mapok().put("data", MapTool.Map().put("foundationId", foundationService.addordeleteFoundationUser(type, foundationId, userId)));
        }
    }

    /*增加或删除项目 管理员权限*/
    @RequestMapping(value = "/addordeleteFoundationProject")
    @ResponseBody
    public Map addordeleteFoundationProject(int type, Integer foundationId, Integer projectId) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() != 2) {
            return MapTool.Map().put("code", 3);
        } else {
            return MapTool.Mapok().put("data", MapTool.Map().put("foundationId", foundationService.addordeleteFoundationProject(type, foundationId, projectId)));
        }
    }
}
