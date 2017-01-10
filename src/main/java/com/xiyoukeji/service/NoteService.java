package com.xiyoukeji.service;

import com.xiyoukeji.entity.Evaluate;
import com.xiyoukeji.entity.Note;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dasiy on 16/12/23.
 */
@Service
public class NoteService {
    @Resource
    BaseDao<Note> baseDao;

    @Transactional
    public List<Note> getNoteList(Integer projectId, int number) {
        if (projectId == null) {
            return baseDao.find("from Note order by create_time desc", 1, number, null);
        } else {
            if (number != 0) {
                return baseDao.find("from Note where project.id = " + projectId + " order by commentTab.id asc, create_time desc", 1, number, null);
            } else {
                return baseDao.find("from Note where project.id = " + projectId + " order by commentTab.id asc, create_time desc");
            }
        }

    }

    @Transactional
    public Integer saveorupdateNote(Note note) {
        note.setCreate_time(Utils.getTime());
        baseDao.saveOrUpdate(note);
        return note.getProject().getId();
    }
}
