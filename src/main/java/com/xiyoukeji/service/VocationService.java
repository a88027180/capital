package com.xiyoukeji.service;

import com.xiyoukeji.entity.*;
import com.xiyoukeji.tools.BaseDao;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by dasiy on 16/12/20.
 */
@Service
public class VocationService {
    @Resource
    BaseDao<Vocation> vocationBaseDao;


    @Transactional
    public List<Vocation> vocationOneList() {
        return vocationBaseDao.find("from Vocation where parent_id = 0");
    }

    @Transactional
    public List<Vocation> vocationTwoList(Integer vocationOneId) {
        return vocationBaseDao.find("from Vocation where parent_id = " + vocationOneId);
    }

    @Transactional
    public List<Vocation> vocationThreeList(Integer vocationTwoId) {
        return vocationBaseDao.find("from Vocation where parent_id = " + vocationTwoId);
    }


}
