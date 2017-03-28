package com.xiyoukeji.service;

import com.xiyoukeji.entity.*;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.MapTool;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by dasiy on 16/12/20.
 */
@Service
public class WXService {
    @Resource
    HttpSession session;
    @Resource
    BaseDao<User> userBaseDao;

    @Resource
    UserService userService;


    @Transactional
    public Map saveWXUser(String openid, String state) {
        User user1 = userBaseDao.get("from User where state_string = '" + state + "'");
        user1.setIsBand(1);
        user1.setOpen_id(openid);
        user1.setState_string("");
        userBaseDao.saveOrUpdate(user1);
        return MapTool.Mapok().put("userId", user1.getId());
    }

    @Transactional
    public Map login(String openid) {
        User user1 = userBaseDao.get("from User where open_id = '" + openid + "'");
        if (user1 == null)
            return MapTool.Map().put("code", "6");/*用户未绑定*/
        else
            return userService.login(user1);
    }

    @Transactional
    public void getStateString(String stateString) {
        User user = (User) session.getAttribute("user");
        User user1 = userBaseDao.get(User.class, user.getId());
        user1.setState_string(stateString);
        userBaseDao.saveOrUpdate(user1);
    }


}
