package com.elegion.test.behancer.di.module;

import com.elegion.test.behancer.di.providers.repository_provider.db_provider.ProjectDbRepositoryProvider;
import com.elegion.test.behancer.di.providers.repository_provider.db_provider.UserDbRepositoryProvider;
import com.elegion.test.behancer.di.providers.repository_provider.db_provider.UserProjectDbRepositoryProvider;
import com.elegion.test.behancer.di.providers.repository_provider.server_provider.ProjectServerRepositoryProvider;
import com.elegion.test.behancer.di.providers.repository_provider.server_provider.UserProjectServerRepositoryProvider;
import com.elegion.test.behancer.di.providers.repository_provider.server_provider.UserServerRepositoryProvider;
import com.lumi.data.repository.project_rep.ProjectDatabaseRepository;
import com.lumi.data.repository.project_rep.ProjectServerRepository;
import com.lumi.data.repository.project_rep.UserProjectDbRepository;
import com.lumi.data.repository.project_rep.UserProjectServerRepository;
import com.lumi.data.repository.user_rep.UserDatabaseRepository;
import com.lumi.data.repository.user_rep.UserServerRepository;
import com.lumi.domain.repository.ProjectRepository;
import com.lumi.domain.repository.UserProjectRepository;
import com.lumi.domain.repository.UserRepository;

import toothpick.config.Module;

public class RepositoryModule extends Module {

    public RepositoryModule(){
        bind(ProjectRepository.class).withName(ProjectRepository.SERVER).toProvider(ProjectServerRepositoryProvider.class).providesSingleton();
        bind(ProjectRepository.class).withName(ProjectRepository.DB).toProvider(ProjectDbRepositoryProvider.class).providesSingleton();

        bind(UserProjectRepository.class).withName(UserRepository.DB).toProvider(UserProjectDbRepositoryProvider.class).providesSingleton();
        bind(UserProjectRepository.class).withName(UserRepository.SERVER).toProvider(UserProjectServerRepositoryProvider.class).providesSingleton();

        bind(UserRepository.class).withName(UserProjectRepository.DB).toProvider(UserDbRepositoryProvider.class).providesSingleton();
        bind(UserRepository.class).withName(UserProjectRepository.SERVER).toProvider(UserServerRepositoryProvider.class).providesSingleton();

    }


}
