package com.elegion.test.behancer.di.providers.repository_provider.server_provider;

import com.lumi.data.api.BehanceApi;
import com.lumi.data.repository.user_rep.UserServerRepository;
import com.lumi.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Provider;

public class UserServerRepositoryProvider implements Provider<UserRepository> {

    @Inject
    BehanceApi mApi;

    @Override
    public UserRepository get() {
        return new UserServerRepository(mApi);
    }
}
