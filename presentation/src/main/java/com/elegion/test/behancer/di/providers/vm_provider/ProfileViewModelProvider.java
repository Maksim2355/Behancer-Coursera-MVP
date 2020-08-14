package com.elegion.test.behancer.di.providers.vm_provider;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.elegion.test.behancer.factory.ProfileViewModelFactory;
import com.elegion.test.behancer.view_model.ProfileViewModel;
import com.lumi.domain.service.user_service.UserService;

import javax.inject.Inject;
import javax.inject.Provider;

public class ProfileViewModelProvider implements Provider<ProfileViewModel> {

    private Fragment mFragment;
    private UserService mUserService;
    private String mUsername;

    @Inject
    public ProfileViewModelProvider(Fragment fragment, UserService userService, String username){
        mFragment = fragment;
        mUserService = userService;
        mUsername = username;
    }

    @Override
    public ProfileViewModel get() {
        ProfileViewModelFactory factory = new ProfileViewModelFactory(mUserService, mUsername);
        return ViewModelProviders.of(mFragment, factory).get(ProfileViewModel.class);
    }
}