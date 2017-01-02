package com.xiyoukeji.service;

import com.xiyoukeji.entity.CommentTab;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.MapTool;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/20.
 */
@Service
public class CommentService {
    @Resource
    BaseDao<CommentTab> baseDao;

    @Transactional
    public Map saveorupdateCommentTab(CommentTab commentTab) {
        baseDao.saveOrUpdate(commentTab);
        return MapTool.Map().put("commentId", commentTab.getId());
    }

    @Transactional
    public void deleteCommentTab(Integer id) {
        /*标签被删除,状态设置成0*/
        CommentTab commentTab = baseDao.get(CommentTab.class, id);
        commentTab.setState(0);
        baseDao.update(commentTab);
    }

    @Transactional
    public List<CommentTab> getCommentTabList() {
        /*返回状态为0的标签*/
        return baseDao.find("from CommentTab where state = 1");
    }
}
