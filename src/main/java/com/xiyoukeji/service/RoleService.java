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
        if (role.getId() == null) {
            /*新建*/
            baseDao.saveOrUpdate(role);
            map.put("roleId", role.getId());
            if (role.getAuthority() != null) {
                map.put("authorityId", role.getAuthority().getId());
            }
        } else {
/*编辑*/
            Role role1 = baseDao.get("from Role where id != " + role.getId() + " and roleName = '" + role.getRoleName() + "'");
            if (role1 != null) {
                return MapTool.Map().put("code", "4");
            } else {
                Role role2 = baseDao.get(Role.class, role.getId());
                role2.setRoleName(role.getRoleName());
                role2.setType(role.getType());
                role2.setUsers(role.getUsers());
                role2.setAuthority(role.getAuthority());
                baseDao.saveOrUpdate(role2);
                return MapTool.Mapok().put("roleId", role.getId());

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
        return baseDao.find("from Role where type != 2 order by type");
    }
}
