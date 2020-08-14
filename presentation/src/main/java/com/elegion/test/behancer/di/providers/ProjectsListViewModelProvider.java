package com.elegion.test.behancer.di.providers;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.elegion.test.behancer.utils.BaseRefreshViewModelFactory;
import com.elegion.test.behancer.view_model.ProjectsListViewModel;
import com.lumi.domain.service.project_service.ProjectService;

import javax.inject.Inject;
import javax.inject.Provider;

public class ProjectsListViewModelProvider implements Provider<ProjectsListViewModel> {

    private Fragment mFragment;
    private ProjectService mProjectService;

    @Inject
    public ProjectsListViewModelProvider(Fragment fragment, ProjectService projectService){
        mFragment = fragment;
        mProjectService = projectService;
    }

    @Override
    public ProjectsListViewModel get() {
        BaseRefreshViewModelFactory factory = new BaseRefreshViewModelFactory(mProjectService);
        return ViewModelProviders.of(mFragment, factory).get(ProjectsListViewModel.class);
    }
}
