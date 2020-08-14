package com.elegion.test.behancer.di.providers.repository_provider.db_provider;

import com.lumi.data.database.BehanceDao;
import com.lumi.data.repository.project_rep.UserProjectDbRepository;
import com.lumi.domain.repository.UserProjectRepository;

import javax.inject.Inject;
import javax.inject.Provider;

public class UserProjectDbRepositoryProvider implements Provider<UserProjectRepository> {

    @Inject
    BehanceDao mDao;


    @Override
    public UserProjectRepository get() {
        return new UserProjectDbRepository(mDao);
    }
}
