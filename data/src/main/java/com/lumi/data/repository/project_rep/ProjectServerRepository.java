package com.lumi.data.repository.project_rep;

import com.lumi.data.BuildConfig;
import com.lumi.data.api.BehanceApi;
import com.lumi.domain.repository.ProjectRepository;
import com.lumi.domain.model.project.Project;
import com.lumi.domain.model.project.ProjectResponse;

import java.util.List;


import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class ProjectServerRepository implements ProjectRepository {

    private BehanceApi mApi;

    @Inject
    public ProjectServerRepository(BehanceApi api){
        mApi = api;
    }

    @Override
    public Single<List<Project>> getProjects() {
        return mApi.getProjects(BuildConfig.API_QUERY).map(new Function<ProjectResponse, List<Project>>() {
            @Override
            public List<Project> apply(ProjectResponse projectResponse) throws Exception {
                return projectResponse.getProjects();
            }
        });
    }

    @Override
    public void insertProjects(List<Project> projectList) {
        //No impl
    }
}
