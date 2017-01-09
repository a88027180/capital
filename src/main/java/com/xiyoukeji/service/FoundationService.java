package com.xiyoukeji.service;

import com.xiyoukeji.entity.Foundation;
import com.xiyoukeji.entity.Project;
import com.xiyoukeji.entity.User;
import com.xiyoukeji.tools.BaseDao;
import com.xiyoukeji.tools.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by dasiy on 16/12/22.
 */
@Service
public class FoundationService {
    @Resource
    BaseDao<Foundation> foundationBaseDao;

    @Resource
    BaseDao<User> userBaseDao;
    @Resource
    BaseDao<Project> projectBaseDao;
    @Resource
    HttpSession session;

    @Transactional
    public Integer saveorupdateFoundation(Foundation foundation) {
        foundationBaseDao.saveOrUpdate(foundation);
        return foundation.getId();

    }

    @Transactional
    public List<Foundation> getFoundationList() {
        User user1 = (User) session.getAttribute("user");
        if (user1.getRole().getType() == 1) {
            return userBaseDao.get(User.class, user1.getId()).getList_foundation();
        } else {
            return foundationBaseDao.find("from Foundation");
        }

    }

    @Transactional
    public Foundation getFoundation(Integer id) {
        return foundationBaseDao.get(Foundation.class, id);
    }

    @Transactional
    public Integer addordeleteFoundationUser(int type, Integer foundationId, Integer userId) {
        Foundation foundation = foundationBaseDao.get(Foundation.class, foundationId);
        User user = userBaseDao.get(User.class, userId);
        switch (type) {
            case 0:
                foundation.getList_user().add(user);
                break;
            case 1:
                foundation.getList_user().remove(user);
                break;
        }
        foundationBaseDao.saveOrUpdate(foundation);
        return foundation.getId();
    }

    @Transactional
    public Integer addordeleteFoundationProject(int type, Integer foundationId, Integer projectId) {
        Foundation foundation = foundationBaseDao.get(Foundation.class, foundationId);
        Project project = projectBaseDao.get(Project.class, projectId);
        switch (type) {
            case 0:

                foundation.getList_project().add(project);
                project.setInvest_current(System.currentTimeMillis());
                project.setState(2);

                break;
            case 1:
                foundation.getList_project().remove(project);
                project.setInvest_current(0);
                project.setState(1);
                break;
        }
        projectBaseDao.saveOrUpdate(project);
        foundationBaseDao.saveOrUpdate(foundation);
        return foundation.getId();
    }
}
