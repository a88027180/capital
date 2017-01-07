package com.xiyoukeji.controller;

import com.google.gson.Gson;
import com.xiyoukeji.beans.ProjectBean;
import com.xiyoukeji.beans.Search;
import com.xiyoukeji.entity.Project;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.service.ProjectService;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.utils.Core;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/21.
 */
@Controller
public class ProjectController {
    @Resource
    ProjectService projectService;
    @Resource
    HttpSession session;


    @ExceptionHandler
    @ResponseBody
    public Map exception(RuntimeException runtimeException) {
        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
    }

    /*新增或编辑项目*/
    @RequestMapping(value = "/saveorupdateProject")
    @ResponseBody
    public Map saveorupdateProject(String strProject, int type) {
        User user1 = (User) session.getAttribute("user");
        Project project = new Gson().fromJson(strProject, Project.class);
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            return MapTool.Mapok().put("data", projectService.saveorupdateProject(project, type));
        }
    }

    /*获取项目列表*/
    @RequestMapping(value = "/getProjectList")
    @ResponseBody
    public Map getProjectList(Search search) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            List<ProjectBean> list = new ArrayList<>();
            Map map = projectService.getProjecList(search);
            List<Project> projects = (List<Project>) map.get("list");
            for (int i = 0; i < projects.size(); i++) {
                ProjectBean projectBean = new ProjectBean();
                Core.assignDest(projectBean, projects.get(i));
                list.add(projectBean);
            }
            return MapTool.Mapok().put("data", MapTool.Map().put("count", map.get("count")).put("list", list));
        }

    }

    /*获取项目*/
    @RequestMapping(value = "/getProject")
    @ResponseBody
    public Map getProject(Integer id) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            ProjectBean projectBean = new ProjectBean();
            Project project = projectService.getProject(id);
            Core.assignDest(projectBean, project);
            return MapTool.Mapok().put("data", MapTool.Map().put("project", projectBean));
        }
    }

    /*添加或删除标签*/
    @RequestMapping(value = "/addordeleteProjectTab")
    @ResponseBody
    public Map addordeleteProjectTab(int type, Integer projectId, String tab) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            return MapTool.Mapok().put("data", MapTool.Map().put("projectId", projectService.addordeleteProjectTab(type, projectId, tab)));
        }
    }

    /*添加或删除项目成员*/
    @RequestMapping(value = "/addordeleteProjectMember")
    @ResponseBody
    public Map addordeleteProjectMember(int type, Integer projectId, Integer userId) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            return MapTool.Mapok().put("data", MapTool.Map().put("projectId", projectService.addordeleteProjectMember(type, projectId, userId)));
        }
    }


}

