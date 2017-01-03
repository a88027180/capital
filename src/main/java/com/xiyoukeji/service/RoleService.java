package com.xiyoukeji.service;

import com.xiyoukeji.entity.Role;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.MapTool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/22.
 */
@Service
public class RoleService {
    @Resource
    BaseDao<Role> baseDao;
    @Resource
    HttpSession session;

    @Transactional
    public Map saveorupdateRole(Role role) {

        Map map = new HashMap<>();
        if (session.getAttribute("userId") == null) {
            map.put("exception", 0);
        } else if ((int) session.getAttribute("roleId") != 2) {
            map.put("exception", 1);
        } else {
            baseDao.saveOrUpdate(role);
            map.put("roleId", role.getId());
            if (role.getAuthority() != null) {
                map.put("authorityId", role.getAuthority().getId());
            }
        }


        return map;

    }

    @Transactional
    public Role getRole(Integer id) {
        return baseDao.get(Role.class, id);
    }

    @Transactional
    public List<Role> getRoleList() {
        return baseDao.find("from Role order by type");
    }
}
