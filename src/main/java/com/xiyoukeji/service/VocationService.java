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

/**
 * Created by dasiy on 16/12/20.
 */
@Service
public class VocationService {
    @Resource
    BaseDao<Vocation> vocationBaseDao;
    @Resource
    SessionFactory sessionFactory;


    @Transactional
    public List<Vocation> vocationOneList() {
        return vocationBaseDao.find("from Vocation where available = 1 and parent_id = 0");
    }

    @Transactional
    public List<Vocation> vocationTwoList(Integer vocationOneId) {
        return vocationBaseDao.find("from Vocation where available = 1 and parent_id = " + vocationOneId);
    }

    @Transactional
    public List<Vocation> vocationThreeList(Integer vocationTwoId) {
        return vocationBaseDao.find("from Vocation where available = 1 and parent_id = " + vocationTwoId);
    }

    @Transactional
    public Map saveorupdateVocation(Vocation vocation) {
        if (vocation.getId() == null) {
            /*新建*/
            Vocation vocation1 = vocationBaseDao.get("from Vocation where name  = '" + vocation.getName() + "'");
            if (vocation1 == null || vocation1.getAvailable() == 0) {
                vocationBaseDao.saveOrUpdate(vocation);
                return MapTool.Mapok().put("vocationId", vocation.getId());
            } else
                return MapTool.Map().put("code", "4");
        } else {
            Vocation vocation1 = vocationBaseDao.get("from Vocation where name = '" + vocation.getName() + "' and id != " + vocation.getId());
            if (vocation1 == null) {
                vocationBaseDao.saveOrUpdate(vocation);
                return MapTool.Mapok().put("vocationId", vocation.getId());
            } else
                return MapTool.Map().put("code", "4");
        }


    }

    @Transactional
    public Map deleteVocation(Integer vocationId) {
        Vocation vocation = vocationBaseDao.get(Vocation.class, vocationId);
        String sql = "";
        switch (vocation.getType()) {
            case 0:
                /*一级*/
                sql = "update vocation set available = 0 where (id = " + vocationId + " or parent_id = " + vocationId + " or double_parent_id = " + vocationId + ")";
                break;
            case 1:
                /*二级*/
                sql = "update vocation set available = 0 where (id = " + vocationId + " or parent_id = " + vocationId + ")";
                break;
            case 2:
                sql = "update vocation set available = 0 where id = " + vocationId;
                break;

        }
        sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
        return MapTool.Mapok();
    }


}
