package com.xiyoukeji.controller;

import com.google.gson.Gson;
import com.xiyoukeji.beans.UserBean;
import com.xiyoukeji.entity.Foundation;
import com.xiyoukeji.entity.Role;
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
import javax.servlet.http.HttpServletResponse;
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

    /*创建或编辑用户 管理员权限*/
    @RequestMapping(value = "/saveorupdateUser")
    @ResponseBody
    public Map saveorupdateUser(String strUser) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() != 2) {
            return MapTool.Map().put("code", 3);
        } else {
            User user = new Gson().fromJson(strUser, User.class);
            return userService.saveorupdateUser(user);
        }
    }

    /*修改密码 管理员权限*/
    @RequestMapping(value = "/updatePassword")
    @ResponseBody
    public Map updatePassword(String prePass, String password) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            return userService.updatePassword(prePass, password);
        }

    }

    /*通过角色id获取用户列表*/
    @RequestMapping(value = "/getUserListByRole")
    @ResponseBody
    public Map getUserListByRole(int roleId) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            List<UserBean> list = new ArrayList<>();
            List<User> users = userService.getUserListByRole(roleId);
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

    }

    /*获取用户列表*/
    @RequestMapping(value = "/getUserList")
    @ResponseBody
    public Map getUserList(int type, Integer roleId, String nameorcode, Integer available) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            List<UserBean> list = new ArrayList<>();
            List<User> users = userService.getUserList(type, roleId, nameorcode, available);
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

    }

    /*获取用户信息*/
    @RequestMapping(value = "/getUser")
    @ResponseBody
    public Map getUser(Integer userId) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            UserBean user = new UserBean();
            try {
                if (userId == null) {
                    Core.assignDest(user, userService.getUser(user1.getId()));
                } else {
                    Core.assignDest(user, userService.getUser(userId));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return MapTool.Mapok().put("data", MapTool.Map().put("user", user));
        }
    }

    /*删除用户信息 管理员权限*/
    @RequestMapping(value = "/update_user")
    @ResponseBody
    public Map update_user(Integer id, int type) {
        Map map = null;
        if (userService.isAuthority())
            map = userService.update_user(id, type);
        return MapTool.Mapok().put("data", map);
//        User user1 = (User) session.getAttribute("user");
//        if (user1 == null) {
//            return MapTool.Map().put("code", 2);
//        } else if (user1.getRole().getType() != 2) {
//            return MapTool.Map().put("code", 3);
//        } else {
//            return MapTool.Mapok().put("data", userService.update_user(id, type));
//        }
    }

    /*登录前端*/
    @RequestMapping(value = "/login")
    @ResponseBody
    public Map login(HttpServletResponse response, User user) {
        return userService.login(response, user);
    }

    /*登录后台*/
    @RequestMapping(value = "/login_back")
    @ResponseBody
    public Map login_back(User user) {
        return userService.login_back(user);
    }

    /*注销*/
    @RequestMapping(value = "/logout")
    @ResponseBody
    public Map logout(HttpServletResponse response) {
        userService.logout(response);
        return MapTool.Mapok();
    }

    /*根据基金id获取所有投资人*/
    @RequestMapping(value = "/getInvestByFoundationId")
    @ResponseBody
    public Map getInvestByFoundationId(Integer foundationId, Integer roleId) {
        Foundation foundation1 = userService.getInvestByFoundationId(foundationId);
        List<User> list = foundation1.getList_user();
        List<UserBean> users = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRole().getId() == roleId) {
                UserBean userBean = new UserBean();
                Core.assignDest(userBean, list.get(i));
                users.add(userBean);
            }
//            if (list.get(i).getRole().getType() == 1) {
//                UserBean userBean = new UserBean();
//                Core.assignDest(userBean, list.get(i));
//                users.add(userBean);
//            }
        }

        return MapTool.Mapok().put("list", users);

    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public Map test(String testString) {
        return MapTool.Mapok().put("string", testString);
    }
}
