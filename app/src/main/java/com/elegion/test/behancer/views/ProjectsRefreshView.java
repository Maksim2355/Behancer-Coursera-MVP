package com.elegion.test.behancer.views;

import androidx.annotation.NonNull;

import com.elegion.test.behancer.data.model.project.Project;
import com.elegion.test.behancer.common.BaseRefreshView;

import java.util.List;

import moxy.viewstate.strategy.alias.AddToEndSingle;
import moxy.viewstate.strategy.alias.Skip;

public interface ProjectsRefreshView extends BaseRefreshView {

    @AddToEndSingle
    void showProjects(@NonNull List<Project> projects);

    @Skip
    void openProfileFragment(@NonNull String username);
}
