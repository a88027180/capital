package com.xiyoukeji.service;

import com.xiyoukeji.beans.Search;
import com.xiyoukeji.beans.UserProjectBean;
import com.xiyoukeji.entity.*;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.MapTool;
import com.xiyoukeji.tools.Utils;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
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
    @Resource
    BaseDao<SearchCities> searchCitiesBaseDao;
    @Resource
    BaseDao<Vocation> vocationBaseDao;
    @Resource
    SessionFactory sessionFactory;
    @Resource
    UserService userService;

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


//                    project1.setVocations(new ArrayList<Vocation>());

                    List<Vocation> alist = project1.getVocations();
                    alist.clear();
                    List<Vocation> vocations = project.getVocations();
                    for (int i = 0; i < vocations.size(); i++) {
                        Vocation vocation = vocationBaseDao.get(Vocation.class, vocations.get(i).getId());
                        /*一级标签*/
                        if (vocation.getType() == 0) {
                            if (!alist.contains(vocation)) {
                                alist.add(vocation);
                            }
                            /*二级标签*/
                        } else if (vocation.getType() == 1) {
                            if (!alist.contains(vocation)) {
                                alist.add(vocation);
                            }
                            Vocation vocation1 = vocationBaseDao.get(Vocation.class, vocation.getParent_id());
                            if (!alist.contains(vocation1)) {
                                alist.add(vocation1);
                            }
                            /*三级标签*/
                        } else if (vocation.getType() == 2) {
                            if (!alist.contains(vocation)) {
                                alist.add(vocation);
                            }
                            Vocation vocation2 = vocationBaseDao.get(Vocation.class, vocation.getParent_id());
                            if (!alist.contains(vocation2)) {
                                alist.add(vocation2);
                            }
                            Vocation vocation3 = vocationBaseDao.get(Vocation.class, vocation2.getParent_id());
                            if ((!alist.contains(vocation3))) {
                                alist.add(vocation3);
                            }
                        }
                    }


//                    project1.setVocations(alist);


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
                    project1.setCity_name(project.getCity_name());
                    project1.setProvince_name(project.getProvince_name());
                    project1.setProject_introducer(project.getProject_introducer());
                    project1.setValuation_afterInvest(project.getValuation_afterInvest());
                    project1.setValuation_state(project.getValuation_state());
                    project1.setShare_common(project.getShare_common());
                    project1.setInvestment_leader(project.getInvestment_leader());
                    project1.setProject_introducer_tel(project.getProject_introducer_tel());
                    project1.setProject_evaluates(project.getProject_evaluates());
                    project1.setEnjoyor(project.getEnjoyor());
                    project1.setInvestment_enjoyor(project.getInvestment_enjoyor());
                    if (project.getInvestment_enjoyor() != null && !project.getInvestment_enjoyor().equals(""))
                        project1.setDouble_enjoyor(Double.valueOf(project.getInvestment_enjoyor().replace(",", "")));
                    if (project.getValuation_afterInvest() != null && !project.getValuation_afterInvest().equals(""))
                        project1.setDouble_valuation(Double.valueOf(project.getValuation_afterInvest().replace(",", "")));
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


        } else

        {
            /*新建 判断名字重复*/
            Project project2 = projectBaseDao.get("from Project where project_name = '" + project.getProject_name() + "' and true_del = 0");
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
        Project project = projectBaseDao.get(Project.class, projectId);
        project.setFalse_del(1);
        project.setFalsedel_current(System.currentTimeMillis());
        projectBaseDao.saveOrUpdate(project);
        return MapTool.Map().put("code", 0);
    }

    @Transactional
    public Map recoverProject(Integer projectId, int type) {
        Project project = projectBaseDao.get(Project.class, projectId);
        switch (type) {
            case 0:
                /*还原*/
                project.setFalse_del(0);
                project.setFalsedel_current(0);
                break;
            case 1:
                /*删除*/
                project.setTrue_del(1);
                project.setTruedel_current(System.currentTimeMillis());
                break;
        }
        projectBaseDao.saveOrUpdate(project);
        return MapTool.Map().put("code", 0);
    }

    @Transactional
    public Map cleanRecycleBin() {
        String sql = "update project set true_del = 1, truedel_current = " + System.currentTimeMillis() + " where false_del = 1";
        sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
        return MapTool.Mapok();
    }

    @Transactional
    public List<Project> getMainProjectList() {
//        return projectBaseDao.find("from Project where state = 2 and exitState = 0 order by invest_current desc");
        User user1 = (User) session.getAttribute("user");
        StringBuffer stringBuffer = new StringBuffer();
        if (user1.getRole().getType() == 1) {
            /*外部角色-投资人*/
            List<Foundation> list = userService.getUser(user1.getId()).getList_foundation();
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    stringBuffer.append(list.get(i).getId());
                    if (i != list.size() - 1)
                        stringBuffer.append(",");
                }
                String sql = "SELECT project.id AS id,project.invest_current AS invest_current,project.project_name AS project_name,file.id AS logo_id,file.url AS logo_url,foundation.name AS foundation_name,project.project_introduction AS project_introduction FROM `project` JOIN project_foundation ON project.id = project_foundation.project_id JOIN foundation ON foundation.id = project_foundation.foundation_id LEFT OUTER JOIN file ON project.logo_id = file.id WHERE project_foundation.foundation_id in (" + stringBuffer.toString() + ") AND state =2 and false_del = 0 order by invest_current desc";
                SQLQuery sqlQuery0 = sessionFactory.getCurrentSession().createSQLQuery(sql);
                List<Object[]> list2 = sqlQuery0.list();
                List<Project> projects = new ArrayList<>();
                for (int i = 0; i < list2.size(); i++) {
                    File file = new File();
                    Foundation foundation = new Foundation();
                    Project project = new Project();
                    project.setId((Integer) list2.get(i)[0]);
                    project.setInvest_current(((BigInteger) list2.get(i)[1]).longValue());
                    project.setProject_name((String) list2.get(i)[2]);
                    project.setProject_introduction((String) list2.get(i)[6]);
                    if (list2.get(i)[3] != null && list2.get(i)[4] != null) {
                        file.setId((Integer) list2.get(i)[3]);
                        file.setUrl((String) list2.get(i)[4]);
                    }
                    foundation.setName((String) list2.get(i)[5]);
                    project.setFoundation(foundation);
                    project.setLogo(file);
                    projects.add(project);
                }
                return projects;
            } else
                return new ArrayList<>();
        } else
            return projectBaseDao.find("from Project where state = 2 and false_del = 0 order by invest_current desc");
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
        /*必输*/
        if (search.getProject_type() != null) {
            switch (search.getProject_type()) {
                case 0:
                    /*我的项目(草稿箱)*/
                    sql += "false_del = 0 and state = 0 and createUser.id = " + user1.getId() + " and ";
                    break;
                case 1:
                    /*所有已发布的项目*/
                    sql += "false_del = 0 and state = 1 and ";
                    break;
                case 2:
                    /*所有已投未退出的项目*/
                    sql += "false_del = 0 and state = 2 and exitState = 0 and ";
                    break;
                case 3:
                    /*所有退出投资的项目*/
                    sql += "false_del = 0 and state = 2 and exitState = 1 and ";
                    break;
                case 4:
                    /*我的项目(包含已发布和已投)*/
                    sql += "false_del = 0 and state != 0 and createUser.id = " + user1.getId() + " and ";
                    break;
                case 5:
                    /*基金下项目列表查询  默认全部*/
                    sql += "false_del = 0 and state = 2 and ";
                    break;
                case 6:
                    /*回收站*/
                    sql += "false_del = 1 and true_del = 0 and ";
                    break;
                case 7:
                    /*所有已投项目(包括未退已退)*/
                    sql += "false_del = 0 and state = 2 and ";
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
                case 6:
                    sql += "falsedel_current >= '" + search.getBegin_time() + "' and ";
                    break;
                case 7:
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
                case 6:
                    sql += "falsedel_current <= '" + search.getEnd_time() + "' and ";
                    break;
                case 7:
                    sql += "invest_current <= '" + search.getEnd_time() + "' and ";
                    break;
            }

        }

        if (search.getSchedule() != null) {
            sql += "project_schedule = " + search.getSchedule() + " and ";
        }

        if (search.getAddress() != null) {
//            sql += "city_name = '" + search.getAddress() + "' and ";
            sql += "city_name like '%" + search.getAddress() + "%' and ";
        }
        if (search.getResource() != null) {
            sql += "project_resource = '" + search.getResource() + "' and ";
        }
        if (search.getUserId() != null) {
            sql += "createUser.id = " + search.getUserId() + " and ";
        }
        sql += "1=1";
        list = projectBaseDao.find(sql, search.getPage(), search.getLine(), null);
        System.out.print(sql);
        long count = projectBaseDao.count(sql);
        map.put("list", list);
        map.put("count", count);
        return map;
    }

    @Transactional
    public Map getProjectList_back(Search search) {
//GROUP by project.id
        String sql = "SELECT project.id,project.project_name,project.province_name,project.city_name,project.project_resource,project.project_stage,user.name as user_name,foundation.name as foundation_name ,evaluateAvg.item_all,project.project_schedule from project JOIN project_note ON project.id = project_note.project_id JOIN note on note.id = project_note.note_id JOIN user_project ON project.id = user_project.project_id JOIN user ON user.id = user_project.user_id LEFT OUTER JOIN project_foundation ON project.id = project_foundation.project_id LEFT OUTER JOIN foundation ON foundation.id = project_foundation.foundation_id LEFT OUTER JOIN project_evaluateAvg ON project.id = project_evaluateAvg.project_id LEFT OUTER JOIN evaluateAvg ON evaluateAvg.id = project_evaluateAvg.evaluateAvg_id WHERE (project.false_del = 0 OR project.false_del is null)";
        String sql1 = "SELECT COUNT(DISTINCT(project.id)) from project JOIN project_note ON project.id = project_note.project_id JOIN note on note.id = project_note.note_id JOIN user_project ON project.id = user_project.project_id JOIN user ON user.id = user_project.user_id LEFT OUTER JOIN project_foundation ON project.id = project_foundation.project_id LEFT OUTER JOIN foundation ON foundation.id = project_foundation.foundation_id LEFT OUTER JOIN project_evaluateAvg ON project.id = project_evaluateAvg.project_id LEFT OUTER JOIN evaluate ON evaluate.id = project_evaluateAvg.evaluateAvg_id WHERE (project.false_del = 0 OR project.false_del is null) ";
        if (search.getFoundationId() != null) {
            sql += "and foundation.id = " + search.getFoundationId() + " and project.state = 2 ";
            sql1 += "and foundation.id = " + search.getFoundationId() + " and project.state = 2 ";

        }
        if (search.getNameorcode() != null) {
            sql += "and (project.project_name like '%" + search.getNameorcode() + "%' or project.project_code like '%" + search.getNameorcode() + "%') ";
            sql1 += "and (project.project_name like '%" + search.getNameorcode() + "%' or project.project_code like '%" + search.getNameorcode() + "%') ";
        }
        if (search.getBegin_time() != 0) {
            sql += "and project.create_current >= '" + search.getBegin_time() + "' ";
            sql1 += "and project.create_current >= '" + search.getBegin_time() + "' ";
        }
        if (search.getEnd_time() != 0) {
            sql += "and project.create_current <= '" + search.getEnd_time() + "' ";
            sql1 += "and project.create_current <= '" + search.getEnd_time() + "' ";
        }
        if (search.getSchedule() != null) {
            sql += "and project.project_schedule = " + search.getSchedule() + " ";
            sql1 += "and project.project_schedule = " + search.getSchedule() + " ";
        }

        if (search.getAddress() != null) {
            sql += "and project.city_name like '%" + search.getAddress() + "%' ";
            sql1 += "and project.city_name like '%" + search.getAddress() + "%' ";
        }
        if (search.getResource() != null) {
            sql += "and project.project_resource = '" + search.getResource() + "' ";
            sql1 += "and project.project_resource = '" + search.getResource() + "' ";
        }
        if (search.getUserId() != null) {
            sql += "and user.id = " + search.getUserId() + " ";
            sql1 += "and user.id = " + search.getUserId() + " ";
        }
        sql += "GROUP by project.id LIMIT " + (search.getPage() - 1) * search.getLine() + "," + search.getLine();
        System.out.print(sql);
        SQLQuery sqlQuery0 = sessionFactory.getCurrentSession().createSQLQuery(sql);
        SQLQuery sqlQuery1 = sessionFactory.getCurrentSession().createSQLQuery(sql1);
        List<Object[]> list = sqlQuery0.list();
        List<BigInteger> list1 = sqlQuery1.list();
        long count = list1.get(0).longValue();

        return MapTool.Map().put("list", list).put("count", count);
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

    @Transactional
    public List<Object[]> getProjectListByUser() {
        User user1 = (User) session.getAttribute("user");
        String sql = "SELECT project.id,project.project_name,project.province_name,project.city_name,project.project_resource,project.project_stage,user.name as user_name,foundation.name as foundation_name ,evaluateAvg.item_all,project.project_schedule from project JOIN project_note ON project.id = project_note.project_id JOIN note on note.id = project_note.note_id JOIN user_project ON project.id = user_project.project_id JOIN user ON user.id = user_project.user_id LEFT OUTER JOIN project_foundation ON project.id = project_foundation.project_id LEFT OUTER JOIN foundation ON foundation.id = project_foundation.foundation_id LEFT OUTER JOIN project_evaluateAvg ON project.id = project_evaluateAvg.project_id LEFT OUTER JOIN evaluateAvg ON evaluateAvg.id = project_evaluateAvg.evaluateAvg_id WHERE note.user_id = " + user1.getId() + " AND (project.false_del = 0 OR project.false_del is null) GROUP by project.id";
        System.out.print(sql);
        SQLQuery sqlQuery0 = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = sqlQuery0.list();
        return list;
    }

    @Transactional
    public Map updateProjectResponser(Integer projectId, Integer userId) {
        User user = userBaseDao.get(User.class, userId);
        Project project = projectBaseDao.get(Project.class, projectId);
        project.setCreateUser(user);
        projectBaseDao.saveOrUpdate(project);
        return MapTool.Mapok();
    }

}
