package com.lumi.domain.service.project_service;

import com.lumi.domain.model.project.Project;

import java.util.List;

import io.reactivex.Single;

public interface ProjectService {

    Single<List<Project>> getProject();

    void insertProject(List<Project> projects);

}
