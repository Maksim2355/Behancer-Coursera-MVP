package com.elegion.test.behancer.di.providers.vm_provider;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.elegion.test.behancer.factory.UserProjectViewModelFactory;
import com.elegion.test.behancer.view_model.UserProjectsViewModel;
import com.lumi.domain.service.userProject_service.UserProjectService;
import javax.inject.Inject;
import javax.inject.Provider;

public class UserProjectsViewModelProvider implements Provider<UserProjectsViewModel> {

    private Fragment mFragment;
    private UserProjectService mUserProjectService;
    private String mUsername;

    @Inject
    public UserProjectsViewModelProvider(Fragment fragment, UserProjectService userProjectService, String username){
        mFragment = fragment;
        mUserProjectService = userProjectService;
        mUsername = username;
    }

    @Override
    public UserProjectsViewModel get() {
        UserProjectViewModelFactory factory = new UserProjectViewModelFactory(mUserProjectService, mUsername);
        return ViewModelProviders.of(mFragment, factory).get(UserProjectsViewModel.class);
    }
}
