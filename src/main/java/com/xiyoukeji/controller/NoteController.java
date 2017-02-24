package com.xiyoukeji.controller;

import com.google.gson.Gson;
import com.xiyoukeji.beans.NoteBean;
import com.xiyoukeji.entity.Evaluate;
import com.xiyoukeji.entity.Note;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.NoteService;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Core;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.nio.charset.CoderResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/23.
 */
@Controller
public class NoteController {
    @Resource
    NoteService noteService;
    @Resource
    HttpSession session;

    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*新建项目笔记*/
    @RequestMapping(value = "/saveorupdateNote")
    @ResponseBody
    public Map saveorupdateNote(String strNote) {
        Note note = new Gson().fromJson(strNote, Note.class);
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            note.setUser(user1);
            return MapTool.Mapok().put("data", MapTool.Map().put("projectId", noteService.saveorupdateNote(note)));
        }
    }

    /*主页项目笔记5条,项目明细中评论*/
    @RequestMapping(value = "/getNoteList")
    @ResponseBody
    public Map getNoteList(Integer projectId, int number) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            List<NoteBean> list0 = new ArrayList<>();
            List<NoteBean> list1 = new ArrayList<>();
            List<Note> notes = noteService.getNoteList(projectId, number);
            for (int i = 0; i < notes.size(); i++) {
                NoteBean noteBean = new NoteBean();
                Core.assignDest(noteBean, notes.get(i));
                if (noteBean.getCommentTab().getType() == 0) {
                    list0.add(noteBean);
                } else {
                    list1.add(noteBean);
                }

            }
            Map map = new HashMap<>();
            map.put("type0", list0);
            map.put("type1", list1);
            return MapTool.Mapok().put("data", map);
        }
    }

    /*通过userId获取noteList*/
    @RequestMapping(value = "/getNoteListByProject")
    @ResponseBody
    public Map getNoteListByProject(Integer projectId) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            List<NoteBean> list = new ArrayList<>();
            List<Note> notes = noteService.getNoteListByProject(projectId);
            for (int i = 0; i < notes.size(); i++) {
                NoteBean noteBean = new NoteBean();
                Core.assignDest(noteBean, notes.get(i));
                list.add(noteBean);
            }
            return MapTool.Mapok().put("data", list);
        }
    }

    /*删除项目笔记*/
    @RequestMapping(value = "/deleteNote")
    @ResponseBody
    public Map deleteNote(Integer noteId) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            return noteService.deleteNote(noteId);
        }
    }
}
