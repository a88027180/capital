package com.xiyoukeji.service;

import com.xiyoukeji.entity.Cities;
import com.xiyoukeji.entity.CommentTab;
import com.xiyoukeji.entity.Provinces;
import com.xiyoukeji.entity.SearchCities;
import com.xiyoukeji.tools.BaseDao;
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

    @Transactional
    public List<Provinces> getProvinceList() {
        return provincesBaseDao.find("from Provinces");
    }

    @Transactional
    public List<Cities> getCityList(String provinceid) {
        return citiesBaseDao.find("from Cities where provinceid = '" + provinceid + "'");
    }

    @Transactional
    public List<SearchCities> getSearchCityList() {
        return searchCitiesBaseDao.find("from SearchCities");
    }


}
