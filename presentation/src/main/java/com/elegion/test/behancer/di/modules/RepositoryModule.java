package com.elegion.test.behancer.di.modules;


import com.lumi.data.repository.project_rep.ProjectDatabaseRepository;
import com.lumi.data.repository.project_rep.ProjectServerRepository;
import com.lumi.data.repository.project_rep.UserProjectDbRepository;
import com.lumi.data.repository.project_rep.UserProjectServerRepository;
import com.lumi.data.repository.user_rep.UserDatabaseRepository;
import com.lumi.data.repository.user_rep.UserServerRepository;
import com.lumi.domain.repository.ProjectRepository;
import com.lumi.domain.repository.UserProjectRepository;
import com.lumi.domain.repository.UserRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    @Named(ProjectRepository.SERVER)
    ProjectRepository provideProjectServerRepository(ProjectServerRepository projectServerRepository){
        return projectServerRepository;
    }

    @Provides
    @Singleton
    @Named(ProjectRepository.DB)
    ProjectRepository provideProjectDatabaseRepository(ProjectDatabaseRepository projectDatabaseRepository){
        return projectDatabaseRepository;
    }

    @Provides
    @Singleton
    @Named(UserRepository.SERVER)
    UserRepository provideUserServerRepository(UserServerRepository userServerRepository){
        return userServerRepository;
    }

    @Provides
    @Singleton
    @Named(UserRepository.DB)
    UserRepository provideUserDatabaseRepository(UserDatabaseRepository userDatabaseRepository){
        return userDatabaseRepository;
    }


    @Provides
    @Singleton
    @Named(UserProjectRepository.SERVER)
    UserProjectRepository provideUserProjectServerRepository(UserProjectServerRepository userProjectServerRepository){
        return userProjectServerRepository;
    }

    @Provides
    @Singleton
    @Named(UserProjectRepository.DB)
    UserProjectRepository provideUserProjectDatabaseRepository(UserProjectDbRepository userProjectDbRepository){
        return userProjectDbRepository;
    }

}
