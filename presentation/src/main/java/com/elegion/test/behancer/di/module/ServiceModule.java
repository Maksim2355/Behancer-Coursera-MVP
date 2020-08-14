package com.elegion.test.behancer.di.module;

import com.elegion.test.behancer.di.providers.service_provider.ProjectServiceProvider;
import com.elegion.test.behancer.di.providers.service_provider.UserProjectServiceProvider;
import com.elegion.test.behancer.di.providers.service_provider.UserServiceProvider;
import com.lumi.domain.service.project_service.ProjectService;
import com.lumi.domain.service.userProject_service.UserProjectService;
import com.lumi.domain.service.user_service.UserService;

import toothpick.config.Module;

public class ServiceModule extends Module {

    public ServiceModule(){
        bind(ProjectService.class).toProvider(ProjectServiceProvider.class).providesSingleton();
        bind(UserService.class).toProvider(UserServiceProvider.class).providesSingleton();
        bind(UserProjectService.class).toProvider(UserProjectServiceProvider.class).providesSingleton();
    }

}
