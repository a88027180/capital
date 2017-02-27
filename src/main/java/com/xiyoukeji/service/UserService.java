package com.xiyoukeji.service;

import com.xiyoukeji.entity.Foundation;
import com.xiyoukeji.entity.Role;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.MapTool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/22.
 */
@Service
public class UserService {
    @Resource
    BaseDao<User> baseDao;
    @Resource
    HttpSession session;
    @Resource
    BaseDao<Role> roleBaseDao;
    @Resource
    BaseDao<Foundation> foundationBaseDao;
    @Resource
    BaseDao<User> userBaseDao;

    @Transactional
    public Map saveorupdateUser(User user) {
        Map map = new HashMap<>();
        User user1 = null;
        if (user.getId() == null) {
            /*新建*/
            User user2 = userBaseDao.get("from User where userName = '" + user.getUserName() + "'");
            if (user2 == null) {
                user1 = user;
                baseDao.saveOrUpdate(user1);
                map.put("userId", user.getId());
                return MapTool.Mapok().put("data", map);
            } else {
                return MapTool.Map().put("code", 4);
            }

        } else {
            user1 = baseDao.get(User.class, user.getId());
            user1.setPassword(user.getPassword());
            user1.setRole(user.getRole());
            user1.setName(user.getName());
            user1.setAddress(user.getAddress());
            user1.setPhone(user.getPhone());
            user1.setPosition(user.getPosition());
            user1.setUserName(user.getUserName());
            user1.setRemark(user.getRemark());
            if (user.getPhoto() != null) {
                user1.setPhoto(user.getPhoto());
            }
            baseDao.saveOrUpdate(user1);
            map.put("userId", user.getId());
            return MapTool.Mapok().put("data", map);
        }


    }

    @Transactional
    public Map updatePassword(String prePass, String password) {
        Map map = new HashMap<>();
        User user = (User) session.getAttribute("user");
        if (!prePass.equals(user.getPassword())) {
            map.put("code", 1);
            return map;
        } else {
            user.setPassword(password);
            userBaseDao.saveOrUpdate(user);
            return MapTool.Map().put("code", 0);
        }


    }

    @Transactional
    public List<User> getUserListByRole(int roleId) {
        List<User> list = new ArrayList<>();
        list = baseDao.find("from User where available = 1 and role.id = " + roleId);
        return list;

    }

    @Transactional
    public List<User> getUserList(int type) {
        List<User> list = new ArrayList<>();
        List<User> list1 = new ArrayList<>();
        switch (type) {
            case 0:
                list = baseDao.find("from User where available = 1 and role.type = 0");
                break;
            case 1:
                list = baseDao.find("from User where available = 1 and role.type = 1");
                break;
            case 2:
                list = baseDao.find("from User where available = 1 and role.type != 2 order by role.type");
                break;
            case 3:
                list = baseDao.find("from User where available = 1 and role.type = 0");
                break;
            case 4:
                /*后台管理系统 禁用启用用户列表*/
                list = baseDao.find("from User WHERE position is NOT NULL AND role.id is NOT NULL ORDER BY role.id ASC,position ASC");
                list1 = baseDao.find("from User WHERE position is  NULL OR role.id is NULL ORDER BY role.id DESC,position DESC");
                list.addAll(list1);
                break;
        }
        return list;

    }

    @Transactional
    public User getUser(Integer id) {
        return baseDao.get(User.class, id);
    }

    @Transactional
    public Map update_user(Integer id, int type) {
        Map map = new HashMap<>();
        User user = baseDao.get(User.class, id);
        /*禁用*/
        if (type == 0) {
            user.setAvailable(0);
        } else {
            /*启用*/
            user.setAvailable(1);
        }
        baseDao.update(user);
        map.put("userId", user.getId());
        return map;
    }

    @Transactional
    public Map login(User user) {
        List<User> list = new ArrayList<>();
        Map map = new HashMap<>();
        map.put("userName", user.getUserName());
        map.put("password", user.getPassword());
        list = baseDao.find("from User where userName = :userName and password = :password and available = 1 ", map);
        if (list.size() != 0) {
            session.setAttribute("user", list.get(0));
            return MapTool.Mapok().put("userId", list.get(0).getId());
        } else {
            return MapTool.Mapok().put("userId", 0);
        }

    }

    @Transactional
    public Map login_back(User user) {
        List<User> list = new ArrayList<>();
        Map map = new HashMap<>();
        map.put("userName", user.getUserName());
        map.put("password", user.getPassword());
        list = baseDao.find("from User where userName = :userName and password = :password and available = 1 ", map);
        if (list.size() != 0) {
            /*管理员*/
            if (list.get(0).getRole().getType() == 2) {
                session.setAttribute("user", list.get(0));
                return MapTool.Mapok().put("userId", list.get(0).getId());
            } else
            /*无权限*/
                return MapTool.Map().put("code", "3");

        } else {
            /*用户名或密码错误*/
            return MapTool.Map().put("code", "1");
        }

    }


    @Transactional
    public void logout() {
        session.removeAttribute("user");
    }
}
