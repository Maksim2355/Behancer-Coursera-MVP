package com.elegion.test.behancer.di.providers.service_provider;

import com.lumi.domain.repository.UserRepository;
import com.lumi.domain.service.user_service.UserService;
import com.lumi.domain.service.user_service.UserServiceImpl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class UserServiceProvider implements Provider<UserService> {

    @Inject
    @Named(UserRepository.SERVER)
    UserRepository mUserServerRepository;

    @Inject
    @Named(UserRepository.DB)
    UserRepository mUserDbRepository;

    @Inject
    public UserServiceProvider(){}


    @Override
    public UserService get() {
        return new UserServiceImpl(mUserServerRepository, mUserDbRepository);
    }
}
