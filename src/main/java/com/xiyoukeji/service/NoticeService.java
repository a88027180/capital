package com.xiyoukeji.service;

import com.xiyoukeji.beans.SearchNotice;
import com.xiyoukeji.entity.*;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.MapTool;
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
public class NoticeService {
    @Resource
    BaseDao<Notice> baseDao;
    @Resource
    HttpSession session;
    @Resource
    BaseDao<User> userBaseDao;
    @Resource
    private SessionFactory sessionFactory;

    @Transactional
    public Integer saveorupdateNotice(Notice notice) {
        notice.setPublish_time(System.currentTimeMillis());
        notice.setSend_user((User) session.getAttribute("user"));
        notice.setType("公告");
        baseDao.saveOrUpdate(notice);
        return notice.getId();
    }

    @Transactional
    public Map getNoticeList(SearchNotice searchNotice) {
        User user = (User) session.getAttribute("user");
        String sql_list = "SELECT * from `notice` LEFT OUTER JOIN `user_notice` on (notice.id = user_notice.notice_id) LEFT OUTER JOIN user ON (user.id = user_notice.user_id) LEFT OUTER JOIN project_notice ON (notice.id = project_notice.notice_id) LEFT OUTER JOIN project ON (project.id = project_notice.project_id) WHERE user.id = " + user.getId() + " AND (project.false_del = 0 or project.false_del IS NULL) ";
        String sql_count = "SELECT COUNT(*) from `notice` LEFT OUTER JOIN `user_notice` on (notice.id = user_notice.notice_id) LEFT OUTER JOIN user ON (user.id = user_notice.user_id) LEFT OUTER JOIN project_notice ON (notice.id = project_notice.notice_id) LEFT OUTER JOIN project ON (project.id = project_notice.project_id) WHERE user.id =" + user.getId() + " AND (project.false_del = 0 or project.false_del IS NULL) ";
        if (searchNotice.getBegin_time() != 0) {
            sql_list += " and notice_time > " + searchNotice.getBegin_time();
            sql_count += " and notice_time > " + searchNotice.getBegin_time();
        }
        if (searchNotice.getEnd_time() != 0) {
            sql_list += " and notice_time < " + searchNotice.getEnd_time();
            sql_count += " and notice_time < " + searchNotice.getEnd_time();
        }
        sql_list += " ORDER BY notice.publish_time DESC ";
        sql_list += "LIMIT " + (searchNotice.getPage() - 1) * searchNotice.getLine() + "," + searchNotice.getPage() * searchNotice.getLine();
        System.out.print(sql_list);
        SQLQuery sqlList = sessionFactory.getCurrentSession().createSQLQuery(sql_list);
        SQLQuery sqlCount = sessionFactory.getCurrentSession().createSQLQuery(sql_count);
//        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM (SELECT * from `notice` LEFT OUTER JOIN `user_notice` on notice.id = user_notice.notice_id WHERE `user_id`= " + user.getId() + ")AS a ORDER by a.`publish_time` DESC");
        List<BigInteger> list1 = sqlCount.list();
        long count = list1.get(0).longValue();
        List<Notice> list = sqlList.addEntity(Notice.class).list();
        return MapTool.Map().put("list", list).put("count", count);
    }

    @Transactional
    public Map getNoticeList_back(SearchNotice searchNotice) {
        String sql_list = "SELECT * from `notice` LEFT OUTER JOIN `user_notice` on (notice.id = user_notice.notice_id) LEFT OUTER JOIN user ON (user.id = user_notice.user_id) LEFT OUTER JOIN project_notice ON (notice.id = project_notice.notice_id) LEFT OUTER JOIN project ON (project.id = project_notice.project_id) WHERE (project.false_del = 0 or project.false_del IS NULL) ";
        String sql_count = "SELECT COUNT(*) from `notice` LEFT OUTER JOIN `user_notice` on (notice.id = user_notice.notice_id) LEFT OUTER JOIN user ON (user.id = user_notice.user_id) LEFT OUTER JOIN project_notice ON (notice.id = project_notice.notice_id) LEFT OUTER JOIN project ON (project.id = project_notice.project_id) WHERE (project.false_del = 0 or project.false_del IS NULL) ";
        if (searchNotice.getBegin_time() != 0) {
            sql_list += " and notice_time > " + searchNotice.getBegin_time();
            sql_count += " and notice_time > " + searchNotice.getBegin_time();
        }
        if (searchNotice.getEnd_time() != 0) {
            sql_list += " and notice_time < " + searchNotice.getEnd_time();
            sql_count += " and notice_time < " + searchNotice.getEnd_time();
        }
        sql_list += " ORDER BY notice.publish_time DESC ";
        sql_list += "LIMIT " + (searchNotice.getPage() - 1) * searchNotice.getLine() + "," + searchNotice.getPage() * searchNotice.getLine();
        System.out.print(sql_list);
        SQLQuery sqlList = sessionFactory.getCurrentSession().createSQLQuery(sql_list);
        SQLQuery sqlCount = sessionFactory.getCurrentSession().createSQLQuery(sql_count);
//        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM (SELECT * from `notice` LEFT OUTER JOIN `user_notice` on notice.id = user_notice.notice_id WHERE `user_id`= " + user.getId() + ")AS a ORDER by a.`publish_time` DESC");
        List<BigInteger> list1 = sqlCount.list();
        long count = list1.get(0).longValue();
        List<Notice> list = sqlList.addEntity(Notice.class).list();
        List<Notice> returnList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!returnList.contains(list.get(i))) {
                returnList.add(list.get(i));
            }
        }
        return MapTool.Map().put("list", returnList).put("count", count);
    }

    @Transactional
    public Notice getNotice(Integer noticeId) {
        return baseDao.get(Notice.class, noticeId);
    }

}
