package com.xiyoukeji.service;

import com.xiyoukeji.entity.Project;
import com.xiyoukeji.entity.ProjectFile;
import com.xiyoukeji.tools.BaseDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by dasiy on 16/12/30.
 */
@Service
public class ProjectFileService {
    @Resource
    BaseDao<ProjectFile> projectFileBaseDao;
    @Resource
    BaseDao<Project> projectBaseDao;

    @Transactional
    public ProjectFile getProjectFile(Integer projectId) {
        return projectFileBaseDao.get("from ProjectFile where project.id = " + projectId);
    }

    @Transactional
    public Integer saveorupdateProjectFile(ProjectFile projectFile, String signDate, String payDate, String payMoney) {
        Project project = projectBaseDao.get(Project.class, projectFile.getProject().getId());
        project.setSign_date(signDate);
        project.setPay_date(payDate);
        project.setPay_money(payMoney);
        projectBaseDao.saveOrUpdate(project);

        ProjectFile projectFile1 = null;
        projectFile1 = projectFileBaseDao.get("from ProjectFile where project.id = " + projectFile.getProject().getId());
        if (projectFile1 != null) {
            projectFile1.setProject(projectFile.getProject());
            projectFile1.setAgreement(projectFile.getAgreement());
            projectFile1.setBriefing(projectFile.getBriefing());
            projectFile1.setProposal(projectFile.getProposal());
            projectFile1.setManages(projectFile.getManages());
            projectFile1.setProfits(projectFile.getProfits());
            projectFile1.setReports(projectFile.getReports());
            projectFile1.setSchedules(projectFile.getSchedules());
        } else {
            projectFile1 = projectFile;
        }
        projectFileBaseDao.saveOrUpdate(projectFile1);
        return projectFile.getProject().getId();
    }
}
