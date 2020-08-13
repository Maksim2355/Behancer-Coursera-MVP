package com.lumi.domain.repository;

import com.lumi.domain.model.project.Project;

import java.util.List;

import io.reactivex.Single;

public interface ProjectRepository {

    String DB = "db";
    String SERVER = "server";

    Single<List<Project>> getProjects();

    void insertProjects(List<Project> projectList);

}
