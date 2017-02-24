package com.xiyoukeji.service;

import com.xiyoukeji.entity.*;
import com.xiyoukeji.tools.BaseDao;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

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
                break;
        }
        return meeting.getId();
    }

    @Transactional
    public Meeting getProjectListFromMeeting() {
        List<Meeting> list = meetingBaseDao.find("from Meeting");
        return list.get(0);

    }
}
