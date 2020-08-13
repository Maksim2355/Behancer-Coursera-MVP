package com.lumi.data.repository.project_rep;

import com.lumi.data.api.BehanceApi;
import com.lumi.domain.model.project.Project;
import com.lumi.domain.model.project.ProjectResponse;
import com.lumi.domain.repository.ProjectRepository;
import com.lumi.domain.repository.UserProjectRepository;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class UserProjectServerRepository implements UserProjectRepository {

    @Inject BehanceApi mApi;

    @Inject
    public UserProjectServerRepository(){ }

    @Override
    public Single<List<Project>> getUserProjects(final String username) {
        return mApi.getUserProjectsInfo(username).map(new Function<ProjectResponse, List<Project>>() {
            @Override
            public List<Project> apply(ProjectResponse projectResponse) throws Exception {
                return projectResponse.getProjects();
            }
        });
    }

    @Override
    public void insertProjects(List<Project> projectList) {
        //noImpl
    }
}
