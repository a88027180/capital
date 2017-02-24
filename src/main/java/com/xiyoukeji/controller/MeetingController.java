package com.xiyoukeji.controller;

import com.xiyoukeji.beans.MeetingBean;
import com.xiyoukeji.beans.SearchCity;
import com.xiyoukeji.entity.Meeting;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.AreaService;
import com.xiyoukeji.service.MeetingService;
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
 * Created by dasiy on 16/12/20.
 */
@Controller
public class MeetingController {
    @Resource
    MeetingService meetingService;
    @Resource
    HttpSession session;


    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*保存本次会议的项目信息*/
    @RequestMapping(value = "/saveorupdateMeeting")
    @ResponseBody
    public Map saveorupdateMeeting(Integer projectId, int type) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            return MapTool.Mapok().put("meeting", meetingService.saveorupdateMeeting(projectId, type));
        }

    }

    /*保存本次会议的项目信息*/
    @RequestMapping(value = "/getProjectListFromMeeting")
    @ResponseBody
    public Map getProjectListFromMeeting() {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            Meeting meeting = meetingService.getProjectListFromMeeting();
            MeetingBean meetingBean = new MeetingBean();
            Core.assignDest(meetingBean, meeting);
            return MapTool.Mapok().put("meeting", meetingBean);
        }

    }


}
