package com.xiyoukeji.controller;

import com.xiyoukeji.beans.CommentTabBean;
import com.xiyoukeji.entity.CommentTab;
import com.xiyoukeji.service.CommentService;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Core;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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


    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*新增或编辑评论标签*/
    @RequestMapping(value = "/saveorupdateCommentTab")
    @ResponseBody
    public Map addCommentTab(CommentTab commentTab) {
        return MapTool.Mapok().put("data", commentService.saveorupdateCommentTab(commentTab));
    }

    /*删除评论标签*/
    @RequestMapping(value = "/deleteCommentTab")
    @ResponseBody
    public Map deleteCommentTab(Integer id) {
        commentService.deleteCommentTab(id);
        return MapTool.Mapok();
    }

    /*获取评论标签*/
    @RequestMapping(value = "/getCommentTabList")
    @ResponseBody
    public Map getCommentTabList() {
        List<CommentTabBean> list = new ArrayList<>();
        List<CommentTab> commentTabs = commentService.getCommentTabList();
        for (int i = 0; i < commentTabs.size(); i++) {
            CommentTabBean commentTabBean = new CommentTabBean();
            Core.assignDest(commentTabBean, commentTabs.get(i));
            list.add(commentTabBean);
        }
        return MapTool.Mapok().put("data", MapTool.Map().put("list", list));
    }
}
