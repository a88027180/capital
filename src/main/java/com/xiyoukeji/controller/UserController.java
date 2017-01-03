package com.xiyoukeji.controller;

import com.xiyoukeji.beans.UserBean;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.UserService;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Core;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/22.
 */
@Controller
public class UserController {
    @Resource
    UserService userService;
    @Resource
    HttpSession session;
    @Resource
    HttpServletRequest request;

    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*创建或编辑用户*/
    @RequestMapping(value = "/saveorupdateUser")
    @ResponseBody
    public Map saveorupdateUser(User user) {
        return MapTool.Mapok().put("data", userService.saveorupdateUser(user));
    }

    /*获取用户列表*/
    @RequestMapping(value = "/getUserList")
    @ResponseBody
    public Map getUserList(int type) {
        List<UserBean> list = new ArrayList<>();
        List<User> users = userService.getUserList(type);
        for (int i = 0; i < users.size(); i++) {
            UserBean user = new UserBean();
            try {
                Core.assignDest(user, users.get(i));
                list.add(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return MapTool.Mapok().put("data", MapTool.Map().put("list", list));
    }

    /*获取用户信息*/
    @RequestMapping(value = "/getUser")
    @ResponseBody
    public Map getUser(Integer id) {
        UserBean user = new UserBean();
        try {
            Core.assignDest(user, userService.getUser(id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return MapTool.Mapok().put("data", MapTool.Map().put("user", user));
    }

    /*删除用户信息*/
    @RequestMapping(value = "/deleteUser")
    @ResponseBody
    public Map deleteUser(Integer id) {
        return MapTool.Mapok().put("data", userService.deleteUser(id));
    }

    /*登录*/
    @RequestMapping(value = "/login")
    @ResponseBody
    public Map login(User user) {
        int flag = userService.login(user);
        return MapTool.Mapok().put("data", MapTool.Map().put("userId", flag));
    }

}
