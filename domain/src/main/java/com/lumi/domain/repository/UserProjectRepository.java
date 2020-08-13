package com.lumi.domain.repository;

import com.lumi.domain.model.project.Project;

import java.util.List;

import io.reactivex.Single;

public interface UserProjectRepository {

    String DB = "db";
    String SERVER = "server";

    Single<List<Project>> getUserProjects(String username);

    void insertProjects(List<Project> projectList);

}
