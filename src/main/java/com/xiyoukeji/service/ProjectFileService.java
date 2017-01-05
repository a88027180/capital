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
    public Integer saveorupdateProjectFile(ProjectFile projectFile) {

        projectFileBaseDao.saveOrUpdate(projectFile);
//        Project project = projectBaseDao.get(Project.class, projectFile.getProject().getId());
//        project.setProposal(projectFile.getProposal());
//        projectBaseDao.saveOrUpdate(project);
        return projectFile.getProject().getId();
    }
}
