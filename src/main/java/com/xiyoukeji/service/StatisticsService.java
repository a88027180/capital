package com.xiyoukeji.service;

import com.xiyoukeji.beans.ExitBean;
import com.xiyoukeji.beans.Statistics;
import com.xiyoukeji.beans.WaitBean;
import com.xiyoukeji.utils.Tools;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasiy on 16/12/20.
 */
@Service
public class StatisticsService {
    @Resource
    SessionFactory sessionFactory;

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
        for (int i = 0;i<list.size();i++){
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

}
