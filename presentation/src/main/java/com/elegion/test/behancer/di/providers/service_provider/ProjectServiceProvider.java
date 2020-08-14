package com.elegion.test.behancer.di.providers.service_provider;

import com.lumi.domain.repository.ProjectRepository;
import com.lumi.domain.service.project_service.ProjectService;
import com.lumi.domain.service.project_service.ProjectServiceImpl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class ProjectServiceProvider implements Provider<ProjectService> {

    @Inject
    @Named(ProjectRepository.SERVER)
    ProjectRepository mProjectServerRepository;

    @Inject
    @Named(ProjectRepository.DB)
    ProjectRepository mProjectDbRepository;

    @Inject
    public ProjectServiceProvider(){}

    @Override
    public ProjectService get() {
        return new ProjectServiceImpl(mProjectServerRepository, mProjectDbRepository);
    }
}
