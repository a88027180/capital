package com.xiyoukeji.controller;

import com.xiyoukeji.beans.CommentTabBean;
import com.xiyoukeji.entity.CommentTab;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.CommentService;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Core;
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


    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*新增或编辑评论标签 管理员权限*/
    @RequestMapping(value = "/saveorupdateCommentTab")
    @ResponseBody
    public Map saveorupdateCommentTab(CommentTab commentTab) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() != 2) {
            return MapTool.Map().put("code", 3);
        } else {
            return MapTool.Mapok().put("commentTabId", commentService.saveorupdateCommentTab(commentTab));
        }
    }

    /*删除评论标签 管理员权限*/
    @RequestMapping(value = "/deleteCommentTab")
    @ResponseBody
    public Map deleteCommentTab(Integer id) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() != 2) {
            return MapTool.Map().put("code", 3);
        } else {
            return MapTool.Mapok().put("CommentTabId", commentService.deleteCommentTab(id));
        }
    }

    /*新增或编辑通知标签 管理员权限*/
    @RequestMapping(value = "/saveorupdateNoticeTab")
    @ResponseBody
    public Map saveorupdateNoticeTab(Integer commentId, String commentText) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() != 2) {
            return MapTool.Map().put("code", 3);
        } else {
            return MapTool.Mapok().put("noticeTabId", commentService.saveorupdateNoticeTab(commentId, commentText));
        }
    }

    /*删除通知标签 管理员权限*/
    @RequestMapping(value = "/deleteNoticeTab")
    @ResponseBody
    public Map deleteNoticeTab(Integer id) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() != 2) {
            return MapTool.Map().put("code", 3);
        } else {
            return MapTool.Mapok().put("NoticeTabId", commentService.deleteNoticeTab(id));
        }
    }


    /*获取评论标签 管理员权限*/
    @RequestMapping(value = "/getCommentTabList")
    @ResponseBody
    public Map getCommentTabList(int type) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() != 2) {
            return MapTool.Map().put("code", 3);
        } else {
            List<CommentTabBean> list = new ArrayList<>();
            List<CommentTab> commentTabs = commentService.getCommentTabList(type);
            for (int i = 0; i < commentTabs.size(); i++) {
                CommentTabBean commentTabBean = new CommentTabBean();
                Core.assignDest(commentTabBean, commentTabs.get(i));
                list.add(commentTabBean);
            }
            return MapTool.Mapok().put("data", MapTool.Map().put("list", list));
        }
    }
}
