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
/*, vocation.id AS vocation_id, vocation.parent_id AS parent_id*/
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
    public Map statistics_project(int type, SearchStatistic searchStatistic) {
        String sql = "";
        String sql0 = "";
        String sql1 = "";
        String sql2 = "";
        String sql3 = "";
        String sql4 = "";

        String con = "";


        sql = " from project JOIN project_vocation ON (project.id = project_vocation.Project_id) JOIN vocation ON (vocation.id = project_vocation.vocations_id) JOIN project_foundation ON (project.id = project_foundation.project_id) JOIN foundation ON (project_foundation.foundation_id = foundation.id) JOIN user_project ON (project.id = user_project.project_id) JOIN user ON (user_project.user_id = user.id) WHERE 1=1";
        if (searchStatistic.getVocationId() != null && searchStatistic.getVocationId() != 0) {
            con += " AND vocation.id = " + searchStatistic.getVocationId();
        }
        if (searchStatistic.getCity() != null && !searchStatistic.getCity().equals("")) {
            con += " AND project.city_name = '" + searchStatistic.getCity() + "'";
        }
        if (searchStatistic.getUserId() != null && searchStatistic.getUserId() != 0) {
            con += " AND user.id = " + searchStatistic.getUserId();
        }
        if (searchStatistic.getFoundationList() != null && !searchStatistic.getFoundationList().equals("")) {
            con += " AND foundation.id IN (" + searchStatistic.getFoundationList() + ")";
        }

        switch (type) {
            case 0:
                /*地区*/
                sql0 = "SELECT COUNT(DISTINCT(project.id)) AS number,SUM(project.money_thisTime) AS invest_money, project.city_name AS city_name";
                sql0 += sql + con + " GROUP BY project.city_name ";
                break;
            case 1:
                /*阶段*/
                sql0 = "SELECT COUNT(DISTINCT(project.id)) AS number,SUM(project.money_thisTime) AS invest_money, project.project_stage AS project_stage";
                sql0 += sql + con + " GROUP BY project.project_stage ";
                break;
            case 2:
                /*评级*/
                sql0 = "SELECT COUNT(DISTINCT(project.id)) AS number,SUM(project.money_thisTime) AS invest_money, project.evaluate AS evaluate";
                sql0 += sql + con + " GROUP BY project.evaluate ";
                break;
            case 3:
                sql0 = "SELECT COUNT(DISTINCT(project.id)) AS number,SUM(project.money_thisTime) AS invest_money, project.project_resource AS project_resource";
                sql0 += sql + con + " GROUP BY project.project_resource ";
                /*来源*/
                break;
            case 4:
                sql0 = "SELECT COUNT(DISTINCT(project.id)) AS number,SUM(project.money_thisTime) AS invest_money, user.id AS user_id, user.name AS user_name";
                sql0 += sql + con + " GROUP BY user.id ";
                /*负责人*/
                break;
        }
        SQLQuery sqlQuery0 = sessionFactory.getCurrentSession().createSQLQuery(sql0);
        List<Object[]> list0 = sqlQuery0.list();

        return MapTool.Mapok().put("data", list0);
    }


}
