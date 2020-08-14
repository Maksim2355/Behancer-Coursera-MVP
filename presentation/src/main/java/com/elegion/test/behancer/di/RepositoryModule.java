package com.elegion.test.behancer.di;

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
        bind(ProjectRepository.class).withName(ProjectRepository.DB).toInstance(new ProjectDatabaseRepository());
        bind(ProjectRepository.class).withName(ProjectRepository.SERVER).toInstance(new ProjectServerRepository());

        bind(UserRepository.class).withName(UserRepository.DB).toInstance(new UserDatabaseRepository());
        bind(UserRepository.class).withName(UserRepository.SERVER).toInstance(new UserServerRepository());

        bind(UserProjectRepository.class).withName(UserProjectRepository.DB).toInstance(new UserProjectDbRepository());
        bind(UserProjectRepository.class).withName(UserProjectRepository.SERVER).toInstance(new UserProjectServerRepository());

    }


}
