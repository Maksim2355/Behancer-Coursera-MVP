package com.lumi.domain.service.project_service;

import com.lumi.domain.model.project.Project;
import com.lumi.domain.repository.ProjectRepository;
import com.lumi.domain.utils.ApiUtils;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class ProjectServiceImpl implements ProjectService {

    @Inject
    @Named(ProjectRepository.SERVER)
    ProjectRepository mProjectServerRepository;

    @Inject
    @Named(ProjectRepository.DB)
    ProjectRepository mProjectDbRepository;

    @Inject
    public ProjectServiceImpl(){}


    @Override
    public Single<List<Project>> getProject() {
        return mProjectServerRepository.getProjects()
                .subscribeOn(Schedulers.io())
                .doOnSuccess(mProjectDbRepository::insertProjects)
                .onErrorReturn(throwable -> ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass())
                        ? mProjectDbRepository.getProjects().blockingGet() : null);
    }

    @Override
    public void insertProject(List<Project> projects) {
        mProjectDbRepository.insertProjects(projects);
    }
}
