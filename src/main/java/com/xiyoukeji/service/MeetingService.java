package com.xiyoukeji.service;

import com.xiyoukeji.entity.*;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.MapTool;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.internal.util.type.PrimitiveWrapperHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by dasiy on 16/12/20.
 */
@Service
public class MeetingService {
    @Resource
    BaseDao<Meeting> meetingBaseDao;
    @Resource
    BaseDao<Cities> citiesBaseDao;
    @Resource
    HttpSession session;
    @Resource
    BaseDao<SearchCities> searchCitiesBaseDao;
    @Resource
    BaseDao<Project> projectBaseDao;
    @Resource
    SessionFactory sessionFactory;

    @Transactional
    public Integer saveorupdateMeeting(Integer projectId, int type) {
        Meeting meeting = null;
        Project project = projectBaseDao.get(Project.class, projectId);
        List<Meeting> list = meetingBaseDao.find("from Meeting");
        switch (type) {
            /*add*/
            case 0:
                if (list.size() == 0) {
                    meeting = new Meeting();
                } else {
                    meeting = list.get(0);
                }
                meeting.getProjects().add(project);
                meetingBaseDao.saveOrUpdate(meeting);
                break;
            case 1:
                /*del*/
                meeting = list.get(0);
                meeting.getProjects().remove(project);
                meetingBaseDao.saveOrUpdate(meeting);
                project.setSequence(0);
                projectBaseDao.saveOrUpdate(project);
                break;
        }
        return meeting.getId();
    }

    @Transactional
    public List<Object[]> getProjectListFromMeeting() {
        String sql = "SELECT project.id,project.project_name,project.province_name,project.city_name,project.project_resource,project.project_stage,user.name as user_name,foundation.name as foundation_name ,evaluate.item_all,project.project_schedule from project JOIN meeting_project ON project.id = meeting_project.project_id JOIN meeting ON meeting_project.meeting_id = meeting.id JOIN user_project ON project.id = user_project.project_id JOIN user ON user.id = user_project.user_id LEFT OUTER JOIN project_foundation ON project.id = project_foundation.project_id LEFT OUTER JOIN foundation ON foundation.id = project_foundation.foundation_id LEFT OUTER JOIN project_evaluate ON project.id = project_evaluate.project_id LEFT OUTER JOIN evaluate ON evaluate.id = project_evaluate.evaluate_id WHERE  (project.false_del = 0 OR project.false_del is null) GROUP by project.id ORDER by project.sequence";
        SQLQuery sqlQuery0 = sessionFactory.getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = sqlQuery0.list();
        return list;

    }

    @Transactional
    public Map meetingProjectListSequence(String ids) {
        String[] sourceStrArray = ids.split(",");
        System.out.print(sourceStrArray.length);
        for (int i = 0; i < sourceStrArray.length; i++) {
            Project project = projectBaseDao.get(Project.class, Integer.valueOf(sourceStrArray[i]));
            project.setSequence(i + 1);
            projectBaseDao.save(project);
        }
        return MapTool.Mapok();

    }
}
