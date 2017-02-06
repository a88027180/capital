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
        baseDao.saveOrUpdate(notice);
        return notice.getId();
    }

    @Transactional
    public Map getNoticeList(SearchNotice searchNotice) {
        User user = (User) session.getAttribute("user");
        String sql_list = "SELECT * FROM (SELECT * from `notice` LEFT OUTER JOIN `user_notice` on notice.id = user_notice.notice_id WHERE `user_id` = " + user.getId();
        String sql_count = "SELECT COUNT(*) from `notice` LEFT OUTER JOIN `user_notice` on notice.id = user_notice.notice_id WHERE `user_id`= " + user.getId();
        if (searchNotice.getBegin_time() != 0) {
            sql_list += " and notice_time > " + searchNotice.getBegin_time();
            sql_count += " and notice_time > " + searchNotice.getBegin_time();
        }
        if (searchNotice.getEnd_time() != 0) {
            sql_list += " and notice_time < " + searchNotice.getEnd_time();
            sql_count += " and notice_time < " + searchNotice.getEnd_time();
        }
        sql_list += ")AS a ORDER by a.`publish_time` DESC";
        sql_list += "LIMIT " + (searchNotice.getPage() - 1) * searchNotice.getLine() + "," + searchNotice.getPage() * searchNotice.getLine();
        System.out.print(sql_list);
        SQLQuery sqlList = sessionFactory.getCurrentSession().createSQLQuery(sql_list);
        SQLQuery sqlCount = sessionFactory.getCurrentSession().createSQLQuery(sql_count);
//        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM (SELECT * from `notice` LEFT OUTER JOIN `user_notice` on notice.id = user_notice.notice_id WHERE `user_id`= " + user.getId() + ")AS a ORDER by a.`publish_time` DESC");
        List<Object[]> list1 = sqlCount.list();
        Long count = (Long) list1.get(0)[0];
        List<Notice> list = sqlList.addEntity(Notice.class).list();
        return MapTool.Map().put("list", list).put("count", count);
    }

    @Transactional
    public Notice getNotice(Integer noticeId) {
        return baseDao.get(Notice.class, noticeId);
    }

}
