package com.xiyoukeji.service;

import com.xiyoukeji.entity.Role;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.MapTool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/22.
 */
@Service
public class RoleService {
    @Resource
    BaseDao<Role> baseDao;

    @Transactional
    public Map saveorupdateRole(Role role) {

        baseDao.saveOrUpdate(role);
        Map map = MapTool.Map().put("roleId", role.getId());
        if (role.getAuthority() != null) {
            map.put("authorityId", role.getAuthority().getId());
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
