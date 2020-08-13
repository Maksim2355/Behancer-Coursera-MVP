package com.lumi.data.repository.project_rep;

import android.util.Pair;

import com.lumi.data.database.BehanceDao;
import com.lumi.domain.repository.ProjectRepository;
import com.lumi.domain.model.project.Cover;
import com.lumi.domain.model.project.Owner;
import com.lumi.domain.model.project.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;

public class ProjectDatabaseRepository implements ProjectRepository {

    @Inject BehanceDao mBehanceDao;

    @Inject
    public ProjectDatabaseRepository(){ }

    @Override
    public Single<List<Project>> getProjects() {
        return Single.fromCallable(new Callable<List<Project>>() {
            @Override
            public List<Project> call() throws Exception {
                List<Project> projects = mBehanceDao.getProjects();
                for (Project project : projects) {
                    project.setCover(mBehanceDao.getCoverFromProject(project.getId()));
                    project.setOwners(mBehanceDao.getOwnersFromProject(project.getId()));
                }
                return projects;
            }
        });
    }

    @Override
    public void insertProjects(List<Project> projectList) {
        mBehanceDao.insertProjects(projectList);

        Pair<List<Cover>, List<Owner>> assembled = assemble(projectList);

        mBehanceDao.clearCoverTable();
        mBehanceDao.insertCovers(assembled.first);
        mBehanceDao.clearOwnerTable();
        mBehanceDao.insertOwners(assembled.second);
    }

    private Pair<List<Cover>, List<Owner>> assemble(List<Project> projects) {
        List<Cover> covers = new ArrayList<>();
        List<Owner> owners = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            Cover cover = projects.get(i).getCover();
            cover.setId(i);
            cover.setProjectId(projects.get(i).getId());
            covers.add(cover);

            Owner owner = projects.get(i).getOwners().get(0);
            owner.setId(i);
            owner.setProjectId(projects.get(i).getId());
            owners.add(owner);
        }
        return new Pair<>(covers, owners);
    }
}
