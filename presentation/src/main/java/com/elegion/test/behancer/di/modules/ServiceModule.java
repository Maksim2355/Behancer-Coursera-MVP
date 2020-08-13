package com.elegion.test.behancer.di.modules;


import com.lumi.data.repository.project_rep.ProjectServerRepository;
import com.lumi.domain.service.project_service.ProjectService;
import com.lumi.domain.service.project_service.ProjectServiceImpl;
import com.lumi.domain.service.userProject_service.UserProjectService;
import com.lumi.domain.service.userProject_service.UserProjectServiceImpl;
import com.lumi.domain.service.user_service.UserService;
import com.lumi.domain.service.user_service.UserServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    @Provides
    @Singleton
    public ProjectService provideProjectService(ProjectServiceImpl projectService){
        return projectService;
    }

    @Provides
    @Singleton
    public UserProjectService provideUserProjectService(UserProjectServiceImpl userProjectService){
        return userProjectService;
    }

    @Provides
    @Singleton
    public UserService provideUserService(UserServiceImpl userService){
        return userService;
    }

}
