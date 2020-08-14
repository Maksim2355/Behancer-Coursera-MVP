package com.elegion.test.behancer.di.providers.repository_provider.db_provider;

import com.lumi.data.api.BehanceApi;
import com.lumi.data.database.BehanceDao;
import com.lumi.data.repository.project_rep.ProjectDatabaseRepository;
import com.lumi.domain.repository.ProjectRepository;

import javax.inject.Inject;
import javax.inject.Provider;

public class ProjectDbRepositoryProvider implements Provider<ProjectRepository> {

    @Inject
    BehanceDao mDao;


    @Override
    public ProjectRepository get() {
        return new ProjectDatabaseRepository(mDao);
    }
}
