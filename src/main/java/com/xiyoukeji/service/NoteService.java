package com.xiyoukeji.service;

import com.xiyoukeji.entity.*;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.tools.Utils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/23.
 */
@Service
public class NoteService {
    @Resource
    BaseDao<Note> baseDao;
    @Resource
    HttpSession session;
    @Resource
    BaseDao<Notice> noticeBaseDao;
    @Resource
    BaseDao<CommentTab> commentTabBaseDao;
    @Resource
    BaseDao<Project> projectBaseDao;

    @Transactional
    public List<Note> getNoteList(Integer projectId, int number) {
        if (projectId == null) {
            if (number != 0)
                return baseDao.find("from Note order by create_time desc", 1, number, null);
            else
                return baseDao.find("from Note order by create_time desc");
        } else {
            if (number != 0) {
                return baseDao.find("from Note where project.id = " + projectId + " order by commentTab.id asc, create_time desc", 1, number, null);
            } else {
                return baseDao.find("from Note where project.id = " + projectId + " order by commentTab.id asc, create_time desc");
            }
        }

    }

    @Transactional
    public List<Note> getNoteListByProject(Integer projectId) {
        User user1 = (User) session.getAttribute("user");
        return baseDao.find("from Note where user_id = " + user1.getId() + " and project.id = " + projectId + " ORDER by create_time DESC");
    }

    @Transactional
    public Integer saveorupdateNote(Note note) {
        note.setCreate_time(Utils.getTime());
        baseDao.saveOrUpdate(note);
        /*给项目创建者发送公告*/
        CommentTab commentTab = commentTabBaseDao.get(CommentTab.class, note.getCommentTab().getId());
        Project project = projectBaseDao.get(Project.class, note.getProject().getId());
        if (commentTab.getNotice() == 1) {
            Notice notice = new Notice();
            notice.setSend_user((User) session.getAttribute("user"));
            notice.setPublish_time(System.currentTimeMillis());
            notice.setNotice_time(System.currentTimeMillis());
            notice.setContent(commentTab.getNotice_text());
            notice.setProject(project);
            List<User> list = new ArrayList<>();
            list.add(project.getCreateUser());
            notice.setRequest_users(list);
            notice.setTitle(commentTab.getNotice_text());
            notice.setType("通知");
            noticeBaseDao.saveOrUpdate(notice);
        }
        return note.getProject().getId();
    }

    @Transactional
    public Map deleteNote(Integer noteId) {
        baseDao.delete(baseDao.get(Note.class, noteId));
        return MapTool.Mapok().put("code", 0);
    }
}
