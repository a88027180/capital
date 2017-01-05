package com.xiyoukeji.controller;

import com.google.gson.Gson;
import com.xiyoukeji.beans.ProjectFileBean;
import com.xiyoukeji.entity.Project;
import com.xiyoukeji.entity.ProjectFile;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.ProjectFileService;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Core;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by dasiy on 16/12/30.
 */
@Controller
public class ProjectFileController {
    @Resource
    ProjectFileService projectFileService;
    @Resource
    HttpSession session;

    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*获取项目文件列表*/
    @RequestMapping(value = "/getProjectFile")
    @ResponseBody
    public Map getProjectFile(Integer projectId) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            ProjectFile projectFile = projectFileService.getProjectFile(projectId);
            ProjectFileBean projectFileBean = new ProjectFileBean();
            Core.assignDest(projectFileBean, projectFile);
            return MapTool.Mapok().put("data", MapTool.Map().put("projectFile", projectFileBean));
        }
    }

    /*新增或编辑项目文件列表*/
    @RequestMapping(value = "/saveorupdateProjectFile")
    @ResponseBody
    public Map saveorupdateProjectFile(String strProjectFile) {
        ProjectFile projectFile = new Gson().fromJson(strProjectFile, ProjectFile.class);
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else if (user1.getRole().getType() != 2) {
            return MapTool.Map().put("code", 3);
        } else {
            return MapTool.Mapok().put("data", MapTool.Map().put("projectId", projectFileService.saveorupdateProjectFile(projectFile)));
        }
    }

}
