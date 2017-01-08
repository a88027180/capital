package com.xiyoukeji.controller;

import com.google.gson.Gson;
import com.xiyoukeji.beans.RoleBean;
import com.xiyoukeji.entity.Project;
import com.xiyoukeji.entity.Role;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.RoleService;
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
public class RoleController {
    @Resource
    RoleService roleService;
    @Resource
    HttpSession session;


    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*创建或编辑角色 管理员权限*/
    @RequestMapping(value = "/saveorupdateRole")
    @ResponseBody
    public Map saveorupdateRole(String strRole) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() != 2) {
            return MapTool.Map().put("code", 3);
        } else {
            Role role = new Gson().fromJson(strRole, Role.class);
            return MapTool.Mapok().put("data", roleService.saveorupdateRole(role));
        }
    }

    /*获取角色信息*/
    @RequestMapping(value = "/getRole")
    @ResponseBody
    public Map getRole(Integer id) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            Role role = roleService.getRole(id);
            RoleBean roleBean = new RoleBean();
            Core.assignDest(roleBean, role);
            return MapTool.Mapok().put("data", MapTool.Map().put("role", roleBean));
        }

    }

    /*获取角色列表*/
    @RequestMapping(value = "/getRoleList")
    @ResponseBody
    public Map getRoleList() {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            List<RoleBean> list = new ArrayList<>();
            List<Role> roles = roleService.getRoleList();
            for (int i = 0; i < roles.size(); i++) {
                RoleBean roleBean = new RoleBean();
                Core.assignDest(roleBean, roles.get(i));
                list.add(roleBean);
            }
            return MapTool.Mapok().put("data", MapTool.Map().put("list", list));
        }
    }
}
