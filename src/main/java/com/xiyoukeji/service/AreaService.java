package com.xiyoukeji.service;

import com.xiyoukeji.entity.*;
import com.xiyoukeji.tools.BaseDao;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasiy on 16/12/20.
 */
@Service
public class AreaService {
    @Resource
    BaseDao<Provinces> provincesBaseDao;
    @Resource
    BaseDao<Cities> citiesBaseDao;
    @Resource
    HttpSession session;
    @Resource
    BaseDao<SearchCities> searchCitiesBaseDao;
    @Resource
    BaseDao<Project> projectBaseDao;
    @Resource
    SessionFactory sessionFactory;

    @Transactional
    public List<Provinces> getProvinceList() {
        return provincesBaseDao.find("from Provinces");
    }

    @Transactional
    public List<Cities> getCityList(String provinceid) {
        return citiesBaseDao.find("from Cities where provinceid = '" + provinceid + "'");
    }

    @Transactional
    public List getSearchCityList(int type, Integer foundationId) {
        User user1 = (User) session.getAttribute("user");

        String sql = "select distinct city_name from Project WHERE 1=1 ";
        switch (type) {
            /*我的项目(草稿箱)*/
            case 0:
                sql += "and state = 0 and createUser.id = " + user1.getId();
                break;
            /*我的项目(已发布和已投)*/
            case 1:
                sql += "and state != 0 and createUser.id = " + user1.getId();
                break;
            /*已发布的项目(未投)*/
            case 2:
                sql += "and state = 1 ";
                break;
            /*已投项目和已退出项目,需要基金Id*/
            case 3:
                sql += "and state = 2 and foundation.id = " + foundationId;
                break;
        }
        return sessionFactory.getCurrentSession().createQuery(sql).list();
    }


}
