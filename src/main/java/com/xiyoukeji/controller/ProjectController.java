package com.xiyoukeji.controller;

import com.google.gson.Gson;
import com.xiyoukeji.beans.*;
import com.xiyoukeji.entity.Project;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.entity.Vocation;
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
            return projectService.saveorupdateProject(project, type);
        }
    }

    /*删除项目*/
    @RequestMapping(value = "/deleteProject")
    @ResponseBody
    public Map deleteProject(Integer projectId) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            return projectService.deleteProject(projectId);
        }
    }

    /*获取主页项目列表*/
    @RequestMapping(value = "/getMainProjectList")
    @ResponseBody
    public Map getMainProjectList() {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            List<Project> projects = projectService.getMainProjectList();
            List<ProjectBean> list = new ArrayList<>();
            for (int i = 0; i < projects.size(); i++) {
                ProjectBean projectBean = new ProjectBean();
                Core.assignDest(projectBean, projects.get(i));
                list.add(projectBean);
            }
            return MapTool.Mapok().put("list", list);
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
            List<VocationBean> vocations = projectBean.getVocations();
            List<VocationBean> list0 = new ArrayList<>();
            List<VocationBean> list1 = new ArrayList<>();
            List<VocationBean> list2 = new ArrayList<>();
            for (int i = 0; i < vocations.size(); i++) {
                if (vocations.get(i).getType() == 0) {
                    list0.add(vocations.get(i));
                } else if (vocations.get(i).getType() == 1) {
                    list1.add(vocations.get(i));

                } else if (vocations.get(i).getType() == 2) {
                    list2.add(vocations.get(i));
                }
            }
//            Map map = new HashMap<>();
//            for (int i = 0; i < list2.size(); i++) {
//                Map map_i = new HashMap<>();
//
//                for (int j = 0; j < list1.size(); j++) {
//                    Map map_j = new HashMap<>();
//
//                    for (int k = 0; k < list0.size(); k++) {
//                        if (list1.get(j).getParent_id() == list0.get(k).getId())
//                            map_j.put("vocationThree", list0.get(k).getName());
//
//                    }
//
//
//                    if (list2.get(i).getParent_id() == list1.get(j).getId())
//
//                        map_i.put(list1.get(j).getName(), map_j);
////
//                }
//                map.put(list2.get(i).getName(), map_i);
//            }


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

