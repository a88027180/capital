package com.xiyoukeji.controller;

import com.xiyoukeji.beans.CommentTabBean;
import com.xiyoukeji.entity.CommentTab;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.CommentService;
import com.xiyoukeji.service.UserService;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Core;
import com.xiyoukeji.utils.ErrCodeExcetion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/20.
 */

@Controller
public class CommentController {
    @Resource
    private CommentService commentService;
    @Resource
    HttpSession session;
    @Resource
    UserService userService;

    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        if (runtimeException instanceof ErrCodeExcetion)
            return MapTool.Map().put("code", ((ErrCodeExcetion) runtimeException).getCode()).put("msg", runtimeException.getMessage());
        else
            return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*新增或编辑评论标签 管理员权限*/
    @RequestMapping(value = "/saveorupdateCommentTab")
    @ResponseBody
    public Map saveorupdateCommentTab(CommentTab commentTab) {
        Map map = null;
        if (userService.isAuthority()) {
            map = commentService.saveorupdateCommentTab(commentTab);
        }
        return map;
    }

    /*删除评论标签 管理员权限*/
    @RequestMapping(value = "/deleteCommentTab")
    @ResponseBody
    public Map deleteCommentTab(Integer id) {
        Map map = null;
        if (userService.isAuthority()) {
            map = MapTool.Mapok().put("CommentTabId", commentService.deleteCommentTab(id));
        }
        return map;
    }

    /*新增或编辑通知标签 管理员权限*/
    @RequestMapping(value = "/saveorupdateNoticeTab")
    @ResponseBody
    public Map saveorupdateNoticeTab(Integer commentId, String commentText) {

        Map map = null;
        if (userService.isAuthority()) {
            map = MapTool.Mapok().put("noticeTabId", commentService.saveorupdateNoticeTab(commentId, commentText));
        }
        return map;
    }

    /*获取标签 管理员权限*/
    @RequestMapping(value = "/getCommentTab")
    @ResponseBody
    public Map getCommentTab(Integer commentId) {

        Map map = null;
        if (userService.isAuthority()) {
            map = MapTool.Mapok().put("CommentTab", commentService.getCommentTab(commentId));
        }
        return map;
    }

    /*删除通知标签 管理员权限*/
    @RequestMapping(value = "/deleteNoticeTab")
    @ResponseBody
    public Map deleteNoticeTab(Integer id) {
        Map map = null;
        if (userService.isAuthority()) {
            map = MapTool.Mapok().put("NoticeTabId", commentService.deleteNoticeTab(id));
        }
        return map;
    }


    /*获取评论标签 管理员权限*/
    @RequestMapping(value = "/getCommentTabList")
    @ResponseBody
    public Map getCommentTabList(int type) {
        Map map = null;
        if (userService.isAuthority()) {
            List<CommentTabBean> list = new ArrayList<>();
            List<CommentTab> commentTabs = commentService.getCommentTabList(type);
            for (int i = 0; i < commentTabs.size(); i++) {
                CommentTabBean commentTabBean = new CommentTabBean();
                Core.assignDest(commentTabBean, commentTabs.get(i));
                list.add(commentTabBean);
            }
            map = MapTool.Mapok().put("data", MapTool.Map().put("list", list));
        }
        return map;
    }
}
