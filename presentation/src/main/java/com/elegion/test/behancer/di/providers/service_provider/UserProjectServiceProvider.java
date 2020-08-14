package com.elegion.test.behancer.di.providers.service_provider;

import com.lumi.domain.repository.ProjectRepository;
import com.lumi.domain.repository.UserProjectRepository;
import com.lumi.domain.service.userProject_service.UserProjectService;
import com.lumi.domain.service.userProject_service.UserProjectServiceImpl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class UserProjectServiceProvider implements Provider<UserProjectService> {

    @Inject
    @Named(UserProjectRepository.SERVER)
    UserProjectRepository mUserProjectServerRepository;

    @Inject
    @Named(UserProjectRepository.DB)
    UserProjectRepository mUserProjectDbRepository;


    @Inject
    public UserProjectServiceProvider(){}

    @Override
    public UserProjectService get() {
        return new UserProjectServiceImpl(mUserProjectServerRepository, mUserProjectDbRepository);
    }
}
