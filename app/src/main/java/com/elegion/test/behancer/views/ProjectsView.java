package com.elegion.test.behancer.views;

import androidx.annotation.NonNull;

import com.elegion.test.behancer.data.model.project.Project;
import com.elegion.test.behancer.common.BaseView;

import java.util.List;

public interface ProjectsView extends BaseView {

    void showProjects(@NonNull List<Project> projects);

    void openProfileFragment(@NonNull String username);
}
