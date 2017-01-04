package com.xiyoukeji.service;

import com.xiyoukeji.beans.Search;
import com.xiyoukeji.entity.*;
import com.xiyoukeji.tools.BaseDao;
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


    @Transactional
    public Map saveorupdateProject(Project project, int type) {
        /*0:项目概要 1:项目资料 2:项目节点 3:视频文件 4:项目发布*/
        Map map = new HashMap<>();

        if (session.getAttribute("user") == null) {
            map.put("exc", 0);
        } else {

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
                    /*如果不是草稿状态选择基金保存 都是投资*/
                        project1.setFinance_record(project.getFinance_record());
                        project1.setFoundation(project.getFoundation());
                        if (project1.getState() != 0) {
                            project1.setInvest_time(Utils.getTime());
                            project1.setState(2);
                        }
                        project1.setMoney_thisTime(project.getMoney_thisTime());
                        project1.setMoney_totalShare(project.getMoney_totalShare());
                        project1.setMoney_eachShare(project.getMoney_eachShare());
                        project1.setSubscription_amount(project.getSubscription_amount());
                        project1.setSubscription_money(project.getSubscription_money());
                        project1.setProject_resource(project.getProject_resource());
                        project1.setProject_address(project.getProject_address());
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
                        project1.setOther(project.getOther());
                        project1.setInvestment_other(project.getInvestment_other());
                        project1.setShare_other(project.getShare_other());
                        break;
                    case 2:
                        project1.setProject_schedule(project.getProject_schedule());
                        if (project.getProject_schedule() == 8) {
                            project1.setState(3);
                        }
                        break;
                    case 3:
                        project1.setVideo(project.getVideo());
                        break;
                    case 4:
                        if (project1.getFoundation() == null) {
                            project1.setState(1);
                        } else {
                            project1.setState(2);
                            project1.setInvest_time(Utils.getTime());
                        }
                        project1.setPublish_time(Utils.getTime());
                        break;
                }
            } else {
            /*新建*/
                project1 = project;
                project1.setCreate_time(Utils.getTime());
                project1.setCreateUser((User) session.getAttribute("user"));
                project1.setState(0);

            }

            projectBaseDao.saveOrUpdate(project1);
            //2093-09-98 66:77:88   567890874
            String s = Utils.getCode(project1.getCreate_time()) + String.format("%04d", project1.getId());
            System.out.print(s);
            project1.setProject_code(s);
            map.put("projectId", project1.getId());
        }
        return map;
    }

    @Transactional
    public Map getProjecList(Search search) {
        User user = (User) session.getAttribute("user");
        Map map = new HashMap<>();
        List<Project> list = new ArrayList<>();
        String sql = "from Project where ";
        if (search.getProjectId() != null) {
            sql += "id = " + search.getProjectId() + " and ";
        }
        if (search.getFoundationId() != null) {
            sql += "foundation.id = " + search.getFoundationId() + " and ";
        }
        if (search.getNameorcode() != null) {
            sql += "project_name like '%" + search.getNameorcode() + "%' or project_code like '%" + search.getNameorcode() + "%' and ";
        }
        if (search.getProject_type() != null) {
            switch (search.getProject_type()) {
                case 0:
                    sql += "state = 0 and createUser.id = " + user.getId();
                    break;
                case 1:
                    sql += "state = 1 and createUser.id = " + user.getId();
                    break;
                case 2:
                    sql += "state = 2 and ";
                    break;
                case 3:
                    sql += "state = 3 and ";
                    break;
            }

        }
        if (search.getBegin_time() != null) {

            switch (search.getProject_type()) {
                case 0:
                    sql += "create_time >= '" + search.getBegin_time() + "' and ";
                    break;
                case 1:
                    sql += "publish_time >= '" + search.getBegin_time() + "' and ";
                    break;
                case 2:
                    sql += "invest_time >= '" + search.getBegin_time() + "' and ";
                    break;
                case 3:
                    sql += "exit_time <= '" + search.getBegin_time() + "' and ";
                    break;
            }
        }
        if (search.getEnd_time() != null) {
            switch (search.getProject_type()) {
                case 0:
                    sql += "create_time <= '" + search.getEnd_time() + "' and ";
                    break;
                case 1:
                    sql += "publish_time <= '" + search.getEnd_time() + "' and ";
                    break;
                case 2:
                    sql += "invest_time <= '" + search.getEnd_time() + "' and ";
                    break;
                case 3:
                    sql += "exit_time <= '" + search.getEnd_time() + "' and ";
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
        if (search.getResponsibility() != null) {
            sql += "createUser.userName like '%" + search.getResponsibility() + "%' and ";
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
