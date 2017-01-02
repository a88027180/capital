package com.xiyoukeji.controller;

import com.xiyoukeji.beans.RoleBean;
import com.xiyoukeji.entity.Role;
import com.xiyoukeji.service.RoleService;
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
public class RoleController {
    @Resource
    RoleService roleService;

    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*创建或编辑角色*/
    @RequestMapping(value = "/saveorupdateRole")
    @ResponseBody
    public Map saveorupdateRole(Role role) {
        return MapTool.Mapok().put("data", roleService.saveorupdateRole(role));
    }

    /*获取角色信息*/
    @RequestMapping(value = "/getRole")
    @ResponseBody
    public Map getRole(Integer id) {
        Role role = roleService.getRole(id);
        RoleBean roleBean = new RoleBean();
        Core.assignDest(roleBean, role);
        return MapTool.Mapok().put("data", MapTool.Map().put("role", roleBean));
    }

    /*获取角色列表*/
    @RequestMapping(value = "/getRoleList")
    @ResponseBody
    public Map getRoleList() {
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
