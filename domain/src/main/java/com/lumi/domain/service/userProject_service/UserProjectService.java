package com.lumi.domain.service.userProject_service;

import com.lumi.domain.model.project.Project;

import java.util.List;

import io.reactivex.Single;

public interface UserProjectService {

    Single<List<Project>> getProject(String username);

    void insertProject(List<Project> projects);

}
