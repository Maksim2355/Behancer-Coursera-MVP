package com.lumi.data.repository.project_rep;

import android.util.Pair;

import com.lumi.data.database.BehanceDao;
import com.lumi.domain.model.project.Cover;
import com.lumi.domain.model.project.Owner;
import com.lumi.domain.model.project.Project;
import com.lumi.domain.repository.UserProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;

public class UserProjectDbRepository implements UserProjectRepository {

    private BehanceDao mBehanceDao;

    @Inject
    public UserProjectDbRepository(BehanceDao dao){
        mBehanceDao = dao;
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

    public void insertProjects(List<Project> projectList) {
        mBehanceDao.insertProjects(projectList);

        Pair<List<Cover>, List<Owner>> assembled = assemble(projectList);

        mBehanceDao.insertCovers(assembled.first);
        mBehanceDao.insertOwners(assembled.second);
    }

    private Pair<List<Cover>, List<Owner>> assemble(List<Project> projects) {
        List<Cover> covers = new ArrayList<>();
        List<Owner> owners = new ArrayList<>();

        for (int i = 0; i < projects.size(); i++) {
            Cover cover = projects.get(i).getCover();
            cover.setProjectId(projects.get(i).getId());
            covers.add(cover);

            Owner owner = projects.get(i).getOwners().get(0);
            owner.setProjectId(projects.get(i).getId());
            owners.add(owner);
        }
        return new Pair<>(covers, owners);
    }
}
