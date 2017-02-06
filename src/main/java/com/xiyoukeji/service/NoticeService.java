package com.xiyoukeji.service;

import com.xiyoukeji.entity.*;
import com.xiyoukeji.tools.BaseDao;
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
    public List<Notice> getNoticeList() {
        User user = (User) session.getAttribute("user");
        SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM (SELECT * from `notice` LEFT OUTER JOIN `user_notice` on notice.id = user_notice.notice_id WHERE `user_id`= " + user.getId() + ")AS a ORDER by a.`publish_time` DESC");
        List<Notice> list = sqlQuery.addEntity(Notice.class).list();
        return list;
    }

    @Transactional
    public Notice getNotice(Integer noticeId) {
        return baseDao.get(Notice.class, noticeId);
    }

}
