package com.lumi.data.repository.user_rep;

import com.lumi.data.database.BehanceDao;
import com.lumi.domain.model.user.Image;
import com.lumi.domain.model.user.User;
import com.lumi.domain.repository.UserRepository;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;

public class UserDatabaseRepository implements UserRepository {

    @Inject BehanceDao mBehanceDao;

    @Inject
    public UserDatabaseRepository() { }

    @Override
    public Single<User> getUser(final String username) {
        return Single.fromCallable(new Callable<User>() {
            @Override
            public User call() throws Exception {
                return mBehanceDao.getUserByName(username);
            }
        });
    }

    @Override
    public void insertUser(User user) {
        Image image = user.getImage();
        image.setId(user.getId());
        image.setUserId(user.getId());

        mBehanceDao.insertUser(user);
        mBehanceDao.insertImage(image);
    }
}
