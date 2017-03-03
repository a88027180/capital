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


//    @ExceptionHandler
//    @ResponseBody
//    public Map exception(RuntimeException runtimeException) {
//        runtimeException.printStackTrace();
//        return MapTool.Map().put("code", "1").put("msg", runtimeException.getMessage());
//    }

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

    /*正常项目删除,放到回收箱*/
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

    /*回收站操作*/
    @RequestMapping(value = "/recoverProject")
    @ResponseBody
    public Map recoverProject(Integer projectId, int type) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            return projectService.recoverProject(projectId, type);
        }
    }

    /*清空回收站*/
    @RequestMapping(value = "/cleanRecycleBin")
    @ResponseBody
    public Map cleanRecycleBin() {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            return projectService.cleanRecycleBin();
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

    /*获取项目列表-前端*/
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

    /*获取已评价项目列表-后台*/
    @RequestMapping(value = "/getProjectList_back")
    @ResponseBody
    public Map getProjectList_back(Search search) {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            Map map = projectService.getProjectList_back(search);
            List<Object[]> list = (List<Object[]>) map.get("list");
            List<ProjectTwoBean> projectTwoBeen = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                ProjectTwoBean projectTwoBean = new ProjectTwoBean();

                projectTwoBean.setProject_id((Integer) list.get(i)[0]);
                projectTwoBean.setProject_name((String) list.get(i)[1]);
                projectTwoBean.setProvince_name((String) list.get(i)[2]);
                projectTwoBean.setCity_name((String) list.get(i)[3]);
                projectTwoBean.setProject_resource((String) list.get(i)[4]);
                projectTwoBean.setProject_stage((String) list.get(i)[5]);
                projectTwoBean.setUser_name((String) list.get(i)[6]);
                projectTwoBean.setFoundation_name((String) list.get(i)[7]);
                projectTwoBean.setItem_all((Integer) list.get(i)[8]);
                projectTwoBean.setProject_schedule((Integer) list.get(i)[9]);

                projectTwoBeen.add(projectTwoBean);

            }

            return MapTool.Mapok().put("list", projectTwoBeen).put("count", map.get("count"));
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

    /*获取项目列表通过userId*/
    @RequestMapping(value = "/getProjectListByUser")
    @ResponseBody
    public Map getProjectListByUser() {
        User user1 = (User) session.getAttribute("user");
        if (user1 == null) {
            return MapTool.Map().put("code", 2);
        } else {
            List<Object[]> list = projectService.getProjectListByUser();
            List<ProjectTwoBean> projectTwoBeen = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                ProjectTwoBean projectTwoBean = new ProjectTwoBean();

                projectTwoBean.setProject_id((Integer) list.get(i)[0]);
                projectTwoBean.setProject_name((String) list.get(i)[1]);
                projectTwoBean.setProvince_name((String) list.get(i)[2]);
                projectTwoBean.setCity_name((String) list.get(i)[3]);
                projectTwoBean.setProject_resource((String) list.get(i)[4]);
                projectTwoBean.setProject_stage((String) list.get(i)[5]);
                projectTwoBean.setUser_name((String) list.get(i)[6]);
                projectTwoBean.setFoundation_name((String) list.get(i)[7]);
                projectTwoBean.setItem_all((Integer) list.get(i)[8]);
                projectTwoBean.setProject_schedule((Integer) list.get(i)[9]);

                projectTwoBeen.add(projectTwoBean);

            }

            return MapTool.Mapok().put("data", projectTwoBeen);
        }
    }


}

