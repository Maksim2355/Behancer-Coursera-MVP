package com.elegion.test.behancer.di.providers.repository_provider.server_provider;

import com.lumi.data.api.BehanceApi;
import com.lumi.data.repository.project_rep.UserProjectServerRepository;
import com.lumi.domain.repository.UserProjectRepository;

import javax.inject.Inject;
import javax.inject.Provider;

public class UserProjectServerRepositoryProvider implements Provider<UserProjectRepository> {

    @Inject
    BehanceApi mApi;

    @Override
    public UserProjectRepository get() {
        return new UserProjectServerRepository(mApi);
    }
}
