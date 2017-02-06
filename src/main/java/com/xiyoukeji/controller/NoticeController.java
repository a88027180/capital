package com.xiyoukeji.controller;

import com.google.gson.Gson;
import com.xiyoukeji.beans.NoticeBean;
import com.xiyoukeji.beans.SearchNotice;
import com.xiyoukeji.entity.CommentTab;
import com.xiyoukeji.entity.Notice;
import com.xiyoukeji.entity.Project;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.NoticeService;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Core;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 17/1/18.
 */
@Controller
public class NoticeController {
    @Resource
    BaseDao<Notice> baseDao;
    @Resource
    NoticeService noticeService;
    @Resource
    HttpSession session;

    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*发布公告*/
    @RequestMapping(value = "/saveorupdateNotice")
    @ResponseBody
    public Map saveorupdateNotice(String strNotice) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            Notice notice = new Gson().fromJson(strNotice, Notice.class);
            return MapTool.Mapok().put("noticeId", noticeService.saveorupdateNotice(notice));
        }
    }

    /*公告列表*/
    @RequestMapping(value = "/getNoticeList")
    @ResponseBody
    public Map getNoticeList(SearchNotice searchNotice) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            List<NoticeBean> list = new ArrayList<>();
            Map map = noticeService.getNoticeList(searchNotice);
            List<Notice> notices = (List<Notice>) map.get("list");
            for (int i = 0; i < notices.size(); i++) {
                NoticeBean noticeBean = new NoticeBean();
                Core.assignDest(noticeBean, notices.get(i));
                list.add(noticeBean);
            }
            return MapTool.Mapok().put("noticeList", list).put("count", map.get("count"));
        }
    }

    /*公告明细*/
    @RequestMapping(value = "/getNotice")
    @ResponseBody
    public Map getNotice(Integer noticeId) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            Notice notice = noticeService.getNotice(noticeId);
            NoticeBean noticeBean = new NoticeBean();
            Core.assignDest(noticeBean, notice);
            return MapTool.Mapok().put("notice", noticeBean);
        }
    }

}
