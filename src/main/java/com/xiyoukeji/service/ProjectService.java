package com.xiyoukeji.service;

import com.xiyoukeji.beans.Search;
import com.xiyoukeji.entity.*;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.tools.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by dasiy on 16/12/21.
 */
@Service
public class ProjectService {
    @Resource
    BaseDao<Project> projectBaseDao;
    @Resource
    BaseDao<User> userBaseDao;
    @Resource
    BaseDao<File> fileBaseDao;
    @Resource
    BaseDao<Foundation> foundationBaseDao;
    @Resource
    HttpSession session;
    @Resource
    BaseDao<InvestmentOther> investmentOtherBaseDao;


    @Transactional
    public Map saveorupdateProject(Project project, int type) {
        /*0:项目概要 1:项目资料 2:项目节点 3:视频文件 4:项目发布*/
        Map map = new HashMap<>();

        Project project1 = null;
        if (project.getId() != null) {
            project1 = projectBaseDao.get(Project.class, project.getId());
            switch (type) {
                case 0:
                    /*商业计划书在项目文件编辑,项目负责人新建不修改,项目编码新建不修改*/
                    project1.setLogo(project.getLogo());
//                        project1.setCreateUser(project.getCreateUser());
//                        project1.setProject_code(project.getProject_code());
                    project1.setProject_name(project.getProject_name());
                    project1.setProject_introduction(project.getProject_introduction());
                    project1.setProject_stage(project.getProject_stage());
                    project1.setValuation(project.getValuation());
                    project1.setTab(project.getTab());
                    project1.setProject_member(project.getProject_member());
                    project1.setProposal(project.getProposal());
                    project1.setProject_reason(project.getProject_reason());
                    project1.setCompany_name(project.getCompany_name());
                    project1.setCompany_web(project.getCompany_web());
                    project1.setCompany_app(project.getCompany_app());
                    project1.setCompany_public(project.getCompany_public());
                    project1.setCompany_founder(project.getCompany_founder());
                    project1.setCompany_tel(project.getCompany_tel());
                    project1.setCompany_contact(project.getCompany_contact());
                    project1.setContact_phone(project.getContact_phone());
                    break;
                case 1:

                    project1.setFinance_record(project.getFinance_record());
                    /*取消投资*/
                    if (project1.getFoundation() != null && project.getFoundation() == null) {
                        project1.setState(1);
                        project1.setInvest_current(0);
                    }
                    /*如果不是草稿状态选择基金保存 都是投资*/
                    if (project1.getState() != 0 && project.getFoundation() != null) {
                        project1.setInvest_current(System.currentTimeMillis());
                        project1.setState(2);
                    }
                    project1.setFoundation(project.getFoundation());

                    project1.setMoney_thisTime(project.getMoney_thisTime());
                    project1.setMoney_totalShare(project.getMoney_totalShare());
                    project1.setMoney_eachShare(project.getMoney_eachShare());
                    project1.setSubscription_amount(project.getSubscription_amount());
                    project1.setSubscription_money(project.getSubscription_money());
                    project1.setProject_resource(project.getProject_resource());
                    project1.setProject_address(project.getProject_address());
                    project1.setProvince_id(project.getProvince_id());
                    project1.setCity_id(project.getCity_id());
                    project1.setProject_introducer(project.getProject_introducer());
                    project1.setValuation_afterInvest(project.getValuation_afterInvest());
                    project1.setValuation_state(project.getValuation_state());
                    project1.setShare_common(project.getShare_common());
                    project1.setInvestment_leader(project.getInvestment_leader());
                    project1.setProject_introducer_tel(project.getProject_introducer_tel());
                    project1.setProject_evaluates(project.getProject_evaluates());
                    project1.setEnjoyor(project.getEnjoyor());
                    project1.setInvestment_enjoyor(project.getInvestment_enjoyor());
                    project1.setShare_enjoyor(project.getShare_enjoyor());
                    List<InvestmentOther> list = investmentOtherBaseDao.find("from InvestmentOther where project.id = " + project.getId());
                    if (list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            investmentOtherBaseDao.delete(list.get(i));
                        }
                    }
                    project1.setInvestment_others(project.getInvestment_others());

                    break;
                case 2:
                    project1.setProject_schedule(project.getProject_schedule());
                    if (project.getProject_schedule() == 8) {
                        project1.setExitState(1);
                        project1.setExit_current(System.currentTimeMillis());
                    } else {
                        project1.setExitState(0);
                        project1.setExit_current(0);
                    }
                    break;
                case 3:
                    File file = new File();
                    file.setFileName("video");
                    file.setUrl(project.getVideo().getUrl());
                    file.setUpdate_current(System.currentTimeMillis());
                    fileBaseDao.saveOrUpdate(file);
                    project1.setVideo(file);
                    break;
                case 4:
                    if (project1.getFoundation() == null) {
                        project1.setState(1);
                    } else {
                        project1.setState(2);
                        project1.setInvest_current(System.currentTimeMillis());
                    }
                    project1.setPublish_current(System.currentTimeMillis());
                    break;
            }

            projectBaseDao.saveOrUpdate(project1);
            project1.setProject_code(Utils.getCode(project1.getCreate_current()));
            map.put("projectId", project1.getId());

            return MapTool.Mapok().put("data", map);


        } else {
            /*新建 判断名字重复*/
            Project project2 = projectBaseDao.get("from Project where project_name = '" + project.getProject_name() + "'");
            if (project2 == null) {
                project1 = project;
                project1.setCreate_current(System.currentTimeMillis());
                project1.setCreateUser((User) session.getAttribute("user"));
                project1.setState(0);

                projectBaseDao.saveOrUpdate(project1);
                project1.setProject_code(Utils.getCode(project1.getCreate_current()));
                map.put("projectId", project1.getId());

                return MapTool.Mapok().put("data", map);

            } else {
                return MapTool.Map().put("code", 4);
            }


        }

    }

    @Transactional
    public Map deleteProject(Integer projectId) {
        projectBaseDao.delete(projectBaseDao.get(Project.class, projectId));
        return MapTool.Map().put("code", 0);
    }

    @Transactional
    public List<Project> getMainProjectList() {
//        return projectBaseDao.find("from Project where state = 2 and exitState = 0 order by invest_current desc");
        return projectBaseDao.find("from Project where state = 2 order by invest_current desc");
    }

    @Transactional
    public Map getProjecList(Search search) {
        User user1 = (User) session.getAttribute("user");
        Map map = new HashMap<>();
        List<Project> list = new ArrayList<>();
        String sql = "from Project where ";
        if (search.getProjectId() != null) {
            sql += "id = " + search.getProjectId() + " and ";
        }
        if (search.getFoundationId() != null) {
            sql += "foundation.id = " + search.getFoundationId() + " and state = 2 and ";
        }
        if (search.getNameorcode() != null) {
            sql += "(project_name like '%" + search.getNameorcode() + "%' or project_code like '%" + search.getNameorcode() + "%') and ";
        }
        if (search.getProject_type() != null) {
            switch (search.getProject_type()) {
                case 0:
                    /*我的项目(草稿箱)*/
                    sql += "state = 0 and createUser.id = " + user1.getId() + " and ";
                    break;
                case 1:
                    /*所有已发布的项目*/
                    sql += "state = 1 and ";
                    break;
                case 2:
                    /*所有已投的项目*/
                    sql += "state = 2 and exitState = 0 and ";
                    break;
                case 3:
                    /*所有退出投资的项目*/
                    sql += "state = 2 and exitState = 1 and ";
                    break;
                case 4:
                    /*我的项目(包含已发布和已投)*/
                    sql += "state != 0 and createUser.id = " + user1.getId() + " and ";
                    break;
                case 5:
                    /*基金下项目列表查询  默认全部*/
                    sql += "state = 2 and ";
                    break;
            }

        }
        if (search.getBegin_time() != 0) {

            switch (search.getProject_type()) {
                case 0:
                    sql += "create_current >= '" + search.getBegin_time() + "' and ";
                    break;
                case 1:
                    sql += "publish_current >= '" + search.getBegin_time() + "' and ";
                    break;
                case 2:
                    sql += "invest_current >= '" + search.getBegin_time() + "' and ";
                    break;
                case 3:
                    sql += "exit_current <= '" + search.getBegin_time() + "' and ";
                    break;
                case 4:
                    sql += "publish_current >= '" + search.getBegin_time() + "' and ";
                    break;
                case 5:
                    sql += "invest_current >= '" + search.getBegin_time() + "' and ";
                    break;
            }
        }
        if (search.getEnd_time() != 0) {
            switch (search.getProject_type()) {
                case 0:
                    sql += "create_current <= '" + search.getEnd_time() + "' and ";
                    break;
                case 1:
                    sql += "publish_current <= '" + search.getEnd_time() + "' and ";
                    break;
                case 2:
                    sql += "invest_current <= '" + search.getEnd_time() + "' and ";
                    break;
                case 3:
                    sql += "exit_current <= '" + search.getEnd_time() + "' and ";
                    break;
                case 4:
                    sql += "publish_current <= '" + search.getEnd_time() + "' and ";
                    break;
                case 5:
                    sql += "invest_current <= '" + search.getEnd_time() + "' and ";
                    break;
            }

        }

        if (search.getSchedule() != null) {
            sql += "project_schedule = " + search.getSchedule() + " and ";
        }

        if (search.getAddress() != null) {
            sql += "project_address like '%" + search.getAddress() + "%' and ";
        }
        if (search.getResource() != null) {
            sql += "project_resource = '" + search.getResource() + "' and ";
        }
        if (search.getUserId() != null) {
            sql += "createUser.id = " + search.getUserId() + " and ";
        }
        sql += "1=1";
        list = projectBaseDao.find(sql, search.getPage(), search.getLine(), null);
        long count = projectBaseDao.count(sql);
        map.put("list", list);
        map.put("count", count);
        return map;
    }

    @Transactional
    public Project getProject(Integer id) {
        return projectBaseDao.get(Project.class, id);
    }

    @Transactional
    public Integer addordeleteProjectTab(int type, Integer projectId, String tab) {
        Project project = projectBaseDao.get(Project.class, projectId);
        switch (type) {
            case 0:
                project.getTab().add(tab);
                break;
            case 1:
                project.getTab().remove(tab);
                break;
        }
        projectBaseDao.saveOrUpdate(project);
        return project.getId();
    }

    @Transactional
    public Integer addordeleteProjectMember(int type, Integer projectId, Integer userId) {
        Project project = projectBaseDao.get(Project.class, projectId);
        User user = userBaseDao.get(User.class, userId);
        switch (type) {
            case 0:
                project.getProject_member().add(user);
                break;
            case 1:
                project.getProject_member().remove(user);
                break;
        }
        projectBaseDao.saveOrUpdate(project);
        return project.getId();
    }


}
