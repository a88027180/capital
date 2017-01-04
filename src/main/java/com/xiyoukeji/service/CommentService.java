package com.xiyoukeji.service;

import com.xiyoukeji.entity.CommentTab;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.MapTool;
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
public class CommentService {
    @Resource
    BaseDao<CommentTab> baseDao;
    @Resource
    HttpSession session;

    @Transactional
    public Map saveorupdateCommentTab(CommentTab commentTab) {


        Map map = new HashMap<>();
        baseDao.saveOrUpdate(commentTab);
        map.put("commentId", commentTab.getId());
        return map;
    }

    @Transactional
    public Map deleteCommentTab(Integer id) {
        /*标签被删除,状态设置成0*/
        Map map = new HashMap<>();
        CommentTab commentTab = baseDao.get(CommentTab.class, id);
        commentTab.setState(0);
        baseDao.update(commentTab);
        map.put("commentId", commentTab.getId());
        return map;


    }

    @Transactional
    public List<CommentTab> getCommentTabList() {
        /*返回状态为0的标签*/
        return baseDao.find("from CommentTab where state = 1");
    }
}
