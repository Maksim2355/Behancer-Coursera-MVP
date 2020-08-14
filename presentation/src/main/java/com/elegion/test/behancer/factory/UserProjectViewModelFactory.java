package com.elegion.test.behancer.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.elegion.test.behancer.view_model.UserProjectsViewModel;
import com.lumi.domain.service.userProject_service.UserProjectService;

public class UserProjectViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private UserProjectService mUserProjectService = null;
    private String mUsername;

    public UserProjectViewModelFactory(UserProjectService userProjectService, String username){
        mUserProjectService = userProjectService;
        mUsername = username;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UserProjectsViewModel(mUserProjectService, mUsername);
    }
}
