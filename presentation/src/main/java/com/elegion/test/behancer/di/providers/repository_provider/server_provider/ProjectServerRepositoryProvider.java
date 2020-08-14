package com.elegion.test.behancer.di.providers.repository_provider.server_provider;

import com.lumi.data.api.BehanceApi;
import com.lumi.data.repository.project_rep.ProjectServerRepository;
import com.lumi.domain.repository.ProjectRepository;

import javax.inject.Inject;
import javax.inject.Provider;

public class ProjectServerRepositoryProvider implements Provider<ProjectRepository> {

    @Inject
    BehanceApi mApi;

    @Override
    public ProjectRepository get() {
        return new ProjectServerRepository(mApi);
    }
}
