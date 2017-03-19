package com.xiyoukeji.service;

import com.xiyoukeji.entity.CommentTab;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.MapTool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/20.
 */
@Service
public class CommentService {
    @Resource
    BaseDao<CommentTab> baseDao;
    @Resource
    HttpSession session;

    @Transactional
    public Map saveorupdateCommentTab(CommentTab commentTab) {
        CommentTab commentTab1 = baseDao.get(CommentTab.class, commentTab.getName());
        if (commentTab1 == null || commentTab1.getState() == 0) {
            baseDao.saveOrUpdate(commentTab);
            return MapTool.Mapok().put("CommentTabId", commentTab.getId());
        } else
            return MapTool.Map().put("code", "4");
    }


    @Transactional
    public Integer deleteCommentTab(Integer id) {
        /*标签被删除,状态设置成0*/
        CommentTab commentTab = baseDao.get(CommentTab.class, id);
        commentTab.setState(0);
        baseDao.update(commentTab);
        return commentTab.getId();


    }

    @Transactional
    public Integer saveorupdateNoticeTab(Integer commentId, String commentText) {
        CommentTab commentTab = baseDao.get(CommentTab.class, commentId);
        commentTab.setNotice(1);
        if (!commentText.equals(""))
            commentTab.setNotice_text(commentText);
        baseDao.saveOrUpdate(commentTab);
        return commentTab.getId();
    }

    @Transactional
    public Integer deleteNoticeTab(Integer id) {
        /*标签被删除,notice设置成0*/
        CommentTab commentTab = baseDao.get(CommentTab.class, id);
        commentTab.setNotice(0);
        baseDao.update(commentTab);
        return commentTab.getId();


    }

    @Transactional
    public List<CommentTab> getCommentTabList(int type) {
        /*返回状态为0的标签*/
        List<CommentTab> list = new ArrayList<>();
        switch (type) {
            /*全部:标签列表*/
            case 0:
                list = baseDao.find("from CommentTab where state = 1");
                break;
            /*通知标签:notice=1*/
            case 1:
                list = baseDao.find("from CommentTab where state = 1 and notice = 1");
                break;
            /*待设置通知标签:notice=0*/
            case 2:
                list = baseDao.find("from CommentTab where state = 1 and notice = 0");
                break;
        }
        return list;
    }

    @Transactional
    public CommentTab getCommentTab(Integer commentId) {
        return baseDao.get(CommentTab.class, commentId);
    }
}
