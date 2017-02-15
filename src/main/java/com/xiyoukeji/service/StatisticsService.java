package com.xiyoukeji.service;

import com.xiyoukeji.beans.ExitBean;
import com.xiyoukeji.beans.SearchStatistic;
import com.xiyoukeji.beans.Statistics;
import com.xiyoukeji.beans.WaitBean;
import com.xiyoukeji.entity.Foundation;
import com.xiyoukeji.entity.Project;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Tools;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/20.
 */
@Service
public class StatisticsService {
    @Resource
    SessionFactory sessionFactory;
    @Resource
    HttpSession session;

    /*已投项目*/
    @Transactional
    public List<Statistics> getType0() {
        List<Statistics> statisticses = new ArrayList<>();
        String sql = "SELECT foundation.id as foundation_id,foundation.name as foundation_name,SUM(project.valuation_afterInvest) as valuation_afterInvest,COUNT(project.id) as project_count ,SUM(project.money_thisTime)AS money_thisTime FROM `project` JOIN project_foundation ON(project.id=project_foundation.project_id) JOIN foundation on(foundation.id=project_foundation.foundation_id) GROUP BY foundation.id";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = sqlQuery.list();
        for (int i = 0; i < list.size(); i++) {
            Statistics statistics = new Statistics();
            Object[] objects = list.get(i);
            if (objects[0] != null)
                statistics.setFoundation_id(objects[0].toString());
            if (objects[1] != null)
                statistics.setFoundation_name(objects[1].toString());
            if (objects[2] != null)
                statistics.setValuation_afterInvest(objects[2].toString());
            if (objects[3] != null)
                statistics.setProject_count(objects[3].toString());
            if (objects[4] != null)
                statistics.setMoney_thisTime(objects[4].toString());
            statisticses.add(statistics);
        }

        return statisticses;
    }

    /*退出项目统计*/
    @Transactional
    public ExitBean getType1() {
        ExitBean exitBean = new ExitBean();
        String sql = "SELECT foundation.id as foundation_id,foundation.name as foundation_name,COUNT(project.id) as project_count FROM `project` JOIN project_foundation ON(project.id=project_foundation.project_id) JOIN foundation on(foundation.id=project_foundation.foundation_id) WHERE project.exitState = 1 GROUP BY foundation.id";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = sqlQuery.list();
        List<Statistics> statisticses = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Statistics statistics = new Statistics();
            Object[] objects = list.get(i);
            if (objects[0] != null)
                statistics.setFoundation_id(objects[0].toString());
            if (objects[1] != null)
                statistics.setFoundation_name(objects[1].toString());
            if (objects[2] != null)
                statistics.setProject_count(objects[2].toString());
            statisticses.add(statistics);
        }
        String sql1 = "SELECT COUNT(*) FROM `project` WHERE `exitState` = 1 and `exit_current` > " + Tools.getCurrentMonth() + " UNION ALL SELECT COUNT(*) FROM `project` WHERE `exitState` = 1 and `exit_current` > " + Tools.getCurrentYear();
        SQLQuery sqlQuery1 = sessionFactory.getCurrentSession().createSQLQuery(sql1);
//        List<Object[]> list1 = sqlQuery1.list();
        exitBean.setList(statisticses);
        List<BigInteger> list1 = sqlQuery1.list();
//        long count = list1.get(0).longValue();
        exitBean.setMonth(list1.get(0).longValue());
        exitBean.setYear(list1.get(1).longValue());


        return exitBean;
    }

    /*未投项目统计*/
    @Transactional
    public WaitBean getType2() {
        WaitBean waitBean = new WaitBean();
        /*1 同意立项 2同意上会 3已上会 4同意投资*/
        String sql = "SELECT COUNT(*) FROM `project` WHERE `project`.`project_schedule` = 1 UNION ALL SELECT COUNT(*) FROM `project` WHERE `project`.`project_schedule` = 2 UNION ALL SELECT COUNT(*) FROM `project` WHERE `project`.`project_schedule` = 3 UNION ALL SELECT COUNT(*) FROM `project` WHERE `project`.`project_schedule` = 4";
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<BigInteger> list = sqlQuery.list();
        waitBean.setOne(list.get(0).longValue());
        waitBean.setTwo(list.get(1).longValue());
        waitBean.setThree(list.get(2).longValue());
        waitBean.setFour(list.get(3).longValue());
        return waitBean;

    }

    @Transactional
    public ExitBean getType3() {
        ExitBean exitBean = new ExitBean();
        String sql = "SELECT COUNT(*) FROM `project` WHERE `project`.`state` = 2 and `project`.`invest_current` > " + Tools.getCurrentMonth() + " UNION ALL SELECT COUNT(*) FROM `project` WHERE `project`.`state` = 2 and `project`.`invest_current` > " + Tools.getCurrentYear();
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<BigInteger> list = sqlQuery.list();
        exitBean.setMonth(list.get(0).longValue());
        exitBean.setYear(list.get(1).longValue());
        return exitBean;
    }

    @Transactional
    public Map vocation_project(SearchStatistic searchStatistic) {
        /*sql0 一级  sql1 二级  sql2三级*/

        String sql = "SELECT COUNT(DISTINCT(project.id)) AS number,vocation.name AS name from project JOIN project_vocation ON (project.id = project_vocation.Project_id) JOIN vocation ON (vocation.id = project_vocation.vocations_id) JOIN project_foundation ON (project.id = project_foundation.project_id) JOIN foundation ON (project_foundation.foundation_id = foundation.id) JOIN user_project ON (project.id = user_project.project_id) JOIN user ON (user_project.user_id = user.id) WHERE 1=1";
        if (searchStatistic.getCity() != null && !searchStatistic.getCity().equals("")) {
            sql += " AND project.city_name = '" + searchStatistic.getCity() + "'";
        }
        if (searchStatistic.getUserId() != null && searchStatistic.getUserId() != 0) {
            sql += " AND user.id = " + searchStatistic.getCity();
        }
        if (searchStatistic.getFoundationList() != null && !searchStatistic.getFoundationList().equals("")) {
            sql += " AND foundation.id IN (" + searchStatistic.getFoundationList() + ")";
        }
        String sql0 = sql + " AND vocation.type = 0 GROUP BY vocation.name";
        String sql1 = sql + " AND vocation.type = 1 GROUP BY vocation.name";
        String sql2 = sql + " AND vocation.type = 2 GROUP BY vocation.name";

        System.out.print(sql0);
        System.out.print(sql1);
        System.out.print(sql2);
        SQLQuery sqlQuery0 = sessionFactory.getCurrentSession().createSQLQuery(sql0);
        List<Object[]> list0 = sqlQuery0.list();
        SQLQuery sqlQuery1 = sessionFactory.getCurrentSession().createSQLQuery(sql1);
        List<Object[]> list1 = sqlQuery1.list();
        SQLQuery sqlQuery2 = sessionFactory.getCurrentSession().createSQLQuery(sql2);
        List<Object[]> list2 = sqlQuery2.list();

        return MapTool.Mapok().put("data0", list0).put("data1", list1).put("data2", list2);
    }


    @Transactional
    public Map statistics_project(int type) {
        switch (type) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
        return null;
    }

    @Transactional
    public Map area_project() {
        User user1 = (User) session.getAttribute("user");
        String sql = "SELECT COUNT(DISTINCT(project.id)) AS number , project.city_name from project JOIN project_vocation ON (project.id = project_vocation.Project_id) JOIN vocation ON (vocation.id = project_vocation.vocations_id) JOIN project_foundation ON (project.id = project_foundation.project_id) JOIN foundation ON (project_foundation.foundation_id = foundation.id)";
        if (user1.getRole().getType() == 1) {
            StringBuffer buffer = new StringBuffer();
            List<Foundation> list = user1.getList_foundation();
            for (int i = 0; i < list.size(); i++) {
                buffer.append(list.get(i).getId());
                if (i != list.size() - 1) {
                    buffer.append(",");
                }
            }
            sql += " WHERE foundation.id IN (" + buffer.toString() + ")";

        }
        sql += " GROUP BY project.city_name";
        System.out.print(sql);
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<Object[]> list1 = sqlQuery.list();
        return MapTool.Mapok().put("data", list1);
    }

    @Transactional
    public Map stage_project() {
        User user1 = (User) session.getAttribute("user");
        String sql = "SELECT COUNT(DISTINCT(project.id)) AS number , project.project_stage from project JOIN project_vocation ON (project.id = project_vocation.Project_id) JOIN vocation ON (vocation.id = project_vocation.vocations_id) JOIN project_foundation ON (project.id = project_foundation.project_id) JOIN foundation ON (project_foundation.foundation_id = foundation.id)";
        if (user1.getRole().getType() == 1) {
            StringBuffer buffer = new StringBuffer();
            List<Foundation> list = user1.getList_foundation();
            for (int i = 0; i < list.size(); i++) {
                buffer.append(list.get(i).getId());
                if (i != list.size() - 1) {
                    buffer.append(",");
                }
            }
            sql += " WHERE foundation.id IN (" + buffer.toString() + ")";

        }
        sql += " GROUP BY project.project_stage";
        System.out.print(sql);
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<Object[]> list1 = sqlQuery.list();
        return MapTool.Mapok().put("data", list1);
    }


}
