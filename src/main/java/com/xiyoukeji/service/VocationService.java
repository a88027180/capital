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
    BaseDao<VocationOne> vocationOneBaseDao;
    @Resource
    BaseDao<VocationTwo> vocationTwoBaseDao;
    @Resource
    BaseDao<VocationThree> vocationThreeBaseDao;


    @Transactional
    public List<VocationOne> vocationOneList() {
        return vocationOneBaseDao.find("from VocationOne");
    }

    @Transactional
    public List<VocationTwo> vocationTwoList(Integer vocationOneId) {
        return vocationTwoBaseDao.find("from VocationTwo where vocationOne.id = " + vocationOneId);
    }

    @Transactional
    public List<VocationThree> vocationThreeList(Integer vocationTwoId) {
        return vocationThreeBaseDao.find("from VocationThree where vocationTwo.id = " + vocationTwoId);
    }


}
