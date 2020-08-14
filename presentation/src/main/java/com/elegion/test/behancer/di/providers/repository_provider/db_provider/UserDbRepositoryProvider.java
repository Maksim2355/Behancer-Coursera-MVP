package com.elegion.test.behancer.di.providers.repository_provider.db_provider;

import com.lumi.data.database.BehanceDao;
import com.lumi.data.repository.user_rep.UserDatabaseRepository;
import com.lumi.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Provider;

public class UserDbRepositoryProvider implements Provider<UserRepository> {

    @Inject
    BehanceDao mDao;


    @Override
    public UserRepository get() {
        return new UserDatabaseRepository(mDao);
    }
}
