package com.lumi.domain.service.userProject_service;

import com.lumi.domain.model.project.Project;
import com.lumi.domain.repository.ProjectRepository;
import com.lumi.domain.repository.UserProjectRepository;
import com.lumi.domain.utils.ApiUtils;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class UserProjectServiceImpl implements UserProjectService {

    private UserProjectRepository mUserProjectServerRepository;
    private UserProjectRepository mUserProjectDbRepository;

    @Inject
    public UserProjectServiceImpl(UserProjectRepository userProjectServerRepository, UserProjectRepository userProjectDbRepository){
        mUserProjectServerRepository = userProjectServerRepository;
        mUserProjectDbRepository = userProjectDbRepository;
    }

    @Override
    public Single<List<Project>> getProject(String username) {
        return mUserProjectServerRepository.getUserProjects(username)
                .subscribeOn(Schedulers.io())
                .doOnSuccess(mUserProjectDbRepository::insertProjects)
                .onErrorReturn(throwable -> ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass())
                        ? mUserProjectDbRepository.getUserProjects(username).blockingGet() : null);
    }

    @Override
    public void insertProject(List<Project> projects) {
        mUserProjectDbRepository.insertProjects(projects);
    }
}
