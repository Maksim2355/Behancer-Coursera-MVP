package com.lumi.data.repository.project_rep;

import com.lumi.data.database.BehanceDao;
import com.lumi.domain.model.project.Project;
import com.lumi.domain.repository.UserProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;

public class UserProjectDbRepository implements UserProjectRepository {

    @Inject BehanceDao mBehanceDao;

    @Inject
    public UserProjectDbRepository(){
    }

    @Override
    public Single<List<Project>> getUserProjects(final String username) {
        return Single.fromCallable(new Callable<List<Project>>() {
            @Override
            public List<Project> call() throws Exception {
                List<Project> projects = mBehanceDao.getProjects();
                for (Project project : projects) {
                    if (mBehanceDao.getOwnersFromProject(project.getId()).get(0).getUsername().equals(username)){
                        project.setOwners(mBehanceDao.getOwnersFromProject(project.getId()));
                        project.setCover(mBehanceDao.getCoverFromProject(project.getId()));
                    }else projects.remove(project);
                }
                return projects;
            }
        });
    }

    @Override
    public void insertProjects(List<Project> projectList) {
        mBehanceDao.insertProjects(projectList);
    }
}
