package com.elegion.test.behancer.di;

import com.lumi.domain.service.project_service.ProjectService;
import com.lumi.domain.service.project_service.ProjectServiceImpl;
import com.lumi.domain.service.userProject_service.UserProjectService;
import com.lumi.domain.service.userProject_service.UserProjectServiceImpl;
import com.lumi.domain.service.user_service.UserService;
import com.lumi.domain.service.user_service.UserServiceImpl;

import toothpick.config.Module;

public class ServiceModule extends Module {

    public ServiceModule(){
        bind(UserService.class).toInstance(new UserServiceImpl());
        bind(UserProjectService.class).toInstance(new UserProjectServiceImpl());
        bind(ProjectService.class).toInstance(new ProjectServiceImpl());

    }

}
