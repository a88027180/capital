package com.xiyoukeji.service;

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

    @Transactional
    public Map saveorupdateUser(User user) {
        Map map = new HashMap<>();
        baseDao.saveOrUpdate(user);
        map.put("userId", user.getId());
        return map;

    }

    @Transactional
    public List<User> getUserList(int type) {
        List<User> list = new ArrayList<>();
        switch (type) {
            case 0:
                list = baseDao.find("from User where available = 1 and role.type = 0");
                break;
            case 1:
                list = baseDao.find("from User where available = 1 and role.type = 1");
                break;
            case 2:
                list = baseDao.find("from User where available = 1 order by role.type");
                break;
            case 3:
                list = baseDao.find("from User where available = 1 and role.type = 0 or role.type = 2");
                break;
        }
        return list;

    }

    @Transactional
    public User getUser(Integer id) {
        return baseDao.get(User.class, id);
    }

    @Transactional
    public Map deleteUser(Integer id) {
        Map map = new HashMap<>();
        User user = baseDao.get(User.class, id);
        user.setAvailable(0);
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
        list = baseDao.find("from User where userName = :userName and password = :password", map);
        if (list.size() != 0) {
            session.setAttribute("user", list.get(0));
            return MapTool.Mapok().put("userId", list.get(0).getId());
        } else {
            return MapTool.Mapok().put("userId", 0);
        }

    }

    @Transactional
    public void logout() {
        session.removeAttribute("user");
    }
}
