package com.elegion.test.behancer.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.elegion.test.behancer.view_model.ProfileViewModel;
import com.lumi.domain.service.project_service.ProjectService;
import com.lumi.domain.service.userProject_service.UserProjectService;
import com.lumi.domain.service.user_service.UserService;

import javax.inject.Inject;

public class ProfileViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private UserService mUserService = null;
    private String mUsername;

    @Inject
    public ProfileViewModelFactory(UserService userService, String username) {
        mUserService = userService;
        mUsername = username;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProfileViewModel(mUserService, mUsername);
    }
}
