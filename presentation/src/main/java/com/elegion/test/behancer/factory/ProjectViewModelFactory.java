package com.elegion.test.behancer.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.elegion.test.behancer.view_model.ProjectsListViewModel;
import com.lumi.domain.service.project_service.ProjectService;


import javax.inject.Inject;

public class ProjectViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private ProjectService mProjectService;

    @Inject
    public ProjectViewModelFactory(ProjectService projectService) {
        mProjectService = projectService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProjectsListViewModel(mProjectService);
    }
}
